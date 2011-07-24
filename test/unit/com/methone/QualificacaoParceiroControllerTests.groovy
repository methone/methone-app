package com.methone

import grails.test.*

import com.methone.enumeration.Interesse
import com.methone.validation.EntityValidationService

class QualificacaoParceiroControllerTests extends ControllerUnitTestCase {

	def parceiroService
	def entityValidationService

	protected void setUp() {
		super.setUp()
		buildMocks()
		controller.metaClass.message = {args -> "${args.code}"}
	}

	protected void tearDown() {
		super.tearDown()
	}

	private void buildMocks(){
		mockDomain(Parceiro)
		mockDomain(Area)
		mockDomain(Especialidade)
		parceiroService = mockFor(ParceiroService)
		entityValidationService = mockFor(EntityValidationService)
		controller.parceiroService = parceiroService.createMock()
		controller.entityValidationService = entityValidationService.createMock()
		entityValidationService.demand.validateVersion()  { entity,versionInUse-> return true }
	}

	private createPersistedEntity(){
		Parceiro p = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
				nome:"nome", telefone: "telefone", endereco : "endereco",
				cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)

		Area area = new Area(nome:"nome",descricao:"descricao")
		area.save()

		def especialidade = new Especialidade(nome:"nome",descricao:"descricao", area:area)
		especialidade.save()

		def especialidadeList = [especialidade]
		p.especialidades = especialidadeList
		p.save()
		return p;
	}

	void testQualificacao() {
		Parceiro p = new Parceiro()
		p.id = 1
		def especialidadeList = [
			new Especialidade(id:1, nome:"nome",descricao:"descricao")
		]
		p.especialidades = especialidadeList

		Area area = new Area(nome:"nome",descricao:"descricao")
		area.save()

		parceiroService.demand.getCurrentUser()  { -> return p }

		def map = controller.qualificacao()
		assertNotNull map
		assertEquals  3, map.size()
		assertNotNull map.parceiroInstance
		assertNotNull map.areaList
		assertNotNull map.especialidadeList
		assertEquals  p, map.parceiroInstance
		assertEquals  1, map.areaList.size()
		assertEquals  1, map.especialidadeList.size()
		assertTrue map.areaList.contains(area)
		assertTrue map.especialidadeList.contains(especialidadeList.get(0))
	}

	void testSalvarParceiroNulo(){
		controller.params.id = 1000
		controller.salvar()
		assertNotNull redirectArgs.action
		assertEquals "qualificacao",redirectArgs.action
		assertNotNull redirectArgs.action
		assertNotNull controller.flash.message
		assertEquals "default.not.found.message", controller.flash.message
	}

	void testSalvarParceiroVersaoInvalida(){
		def parceiro = createPersistedEntity()
		controller.params.id = parceiro.id
		controller.params.version = 1

		Area area = new Area(nome:"nome",descricao:"descricao")
		area.save()

		entityValidationService.demand.validateVersion()  { entity,versionInUse -> return false }
		controller.salvar()

		assertNotNull renderArgs.view
		assertEquals "qualificacao", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.parceiroInstance
		assertEquals parceiro, renderArgs.model.parceiroInstance
		assertNotNull renderArgs.model.parceiroInstance
		assertNotNull renderArgs.model.areaList
		assertNotNull renderArgs.model.especialidadeList
		assertEquals  parceiro, renderArgs.model.parceiroInstance
		assertEquals  2, renderArgs.model.areaList.size()
		assertEquals  1, renderArgs.model.especialidadeList.size()
		assertTrue renderArgs.model.areaList.contains(area)
		assertEquals renderArgs.model.especialidadeList, parceiro.especialidades
	}

	void testSalvoSucesso(){

		entityValidationService.demand.validateVersion()  { entity,versionInUse -> return true }
		def area = new Area(nome:"area_1",descricao:"area_1")
		area.save()

		def especialidade_1 = new Especialidade(nome:"nome_1",descricao:"descricao_1",area:area)
		def especialidade_2 = new Especialidade(nome:"nome_2",descricao:"descricao_2",area:area)

		especialidade_1.save()
		especialidade_2.save()

		def parceiro = createPersistedEntity()

		controller.params.especialidade = [
			especialidade_1.id,
			especialidade_2 .id
		]
		controller.params.id  = parceiro.id
		controller.salvar()
		assertNotNull redirectArgs.action
		assertEquals "qualificacao",redirectArgs.action
		assertNotNull redirectArgs.action
		assertNotNull controller.flash.message
		assertEquals "salvoSucesso", controller.flash.message
		assertNotNull parceiro.especialidades
		assertEquals 2, parceiro.especialidades.size()

		assertTrue especialidade_1 in parceiro.especialidades
		assertTrue especialidade_2 in parceiro.especialidades

		controller.params.especialidade = null
		controller.salvar()

		assertNotNull redirectArgs.action
		assertEquals "qualificacao",redirectArgs.action
		assertNotNull redirectArgs.action
		assertNotNull controller.flash.message
		assertEquals "salvoSucesso", controller.flash.message
		assertNull parceiro.especialidades
	}
}
