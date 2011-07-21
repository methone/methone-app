package com.methone

import grails.test.*

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
		entityValidationService.demand.validateVersion()  { entity,versionInUse->
			return true
		}
	}

	private createPersistedEntity(){
		Parceiro p = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS, version : 1)
		p.save()
		return p;
	}

    void testQualificacao() {
		Parceiro p = new Parceiro()
		p.id = 1
		def especialidadeList = [new Especialidade(id:1, nome:"nome",descricao:"descricao")]
		p.especialidades = especialidadeList

		Area area = new Area(nome:"nome",descricao:"descricao")
		area.save()

		parceiroService.demand.getCurrentUser()  { ->
			return p
		}

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
}
