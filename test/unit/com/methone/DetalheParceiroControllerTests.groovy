package com.methone

import grails.test.*

import com.methone.enumeration.Interesse
import com.methone.validation.EntityValidationService
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Any;

class DetalheParceiroControllerTests extends ControllerUnitTestCase {
	def parceiroService
	def entityValidationService

	protected void setUp() {
        super.setUp()
		buildMocks()
		controller.metaClass.message = {args -> println "${args}"}
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDetail() {
		Parceiro p = new Parceiro()
		p.id = 1
		parceiroService.demand.getCurrentUser()  { ->
			return p
		}
		controller.parceiroService.diretorioImagemRelativo = "diretorio"
		def map = controller.detail()
		assertNotNull map
		assertEquals  2, map.size()
		assertNotNull map.parceiroInstance
		assertTrue map.parceiroInstance instanceof Parceiro
		assertNotNull  map.parceiroInstance.id

		assertNotNull map.diretorioImagem
		assertEquals controller.parceiroService.diretorioImagemRelativo, map.diretorioImagem
    }

	void testUpdateVersionInvalida(){
		def parceiro = createPersistedEntity()
		controller.params.id = parceiro.id
		controller.params.version = 1
		entityValidationService.demand.validateVersion()  { entity,versionInUse ->
			return false
		}
		controller.update()
		assertNotNull renderArgs.view
		assertEquals "detail", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.parceiroInstance
		assertEquals parceiro, renderArgs.model.parceiroInstance
	}

	void testUpdateSucesso(){
		parceiroService.demand.updateDetalheParceiro()  { parceiroInstance, nomeOriginal, file ->
			return true
		}
		def entity = createPersistedEntity()
		controller.params.id = entity.id
		controller.update();
		assertNotNull redirectArgs.action
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "detail", redirectArgs.action
	}

	void testUpdateErroValidacao(){
		parceiroService.demand.updateDetalheParceiro()  { parceiroInstance, nomeOriginal, file ->
			return false
		}
		def entity = createPersistedEntity()
		mockParams()
		def set = controller.params.keySet()
		for(element in set){
			controller.params."${element}" = null
		}
		controller.params.id = entity.id
		controller.update();
		assertNotNull renderArgs.view
		assertEquals "detail", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.parceiroInstance
		assertEquals entity, renderArgs.model.parceiroInstance
	}

	void testUpdateErro(){
		def map = controller.update()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "detail", redirectArgs.action
	}

	private void buildMocks(){
		mockDomain(Parceiro)
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

	private void mockParams(){
		def map = ['username' : "username", 'email' : "teste@teste.com", 'password': "senha",
         'nome':"nome", 'telefone': "telefone", 'endereco' : "endereco",
			'cep' : "cep", 'estado': "estado", 'cidade' : "cidade", 'interesse': Interesse.AMBOS.name()]
		controller.params.putAll(map)
	}
}
