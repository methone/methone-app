package com.methone

import grails.test.*

import com.methone.enumeration.Interesse

class DetalheParceiroControllerTests extends ControllerUnitTestCase {
	def parceiroService

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
		def map = controller.detail()
		assertNotNull map
		assertEquals  1, map.size()
		assertNotNull map.parceiroInstance
		assertTrue map.parceiroInstance instanceof Parceiro
		assertNotNull  map.parceiroInstance.id
    }

	void testUpdateVersionInvalida(){
		def entity = createPersistedEntity()
		controller.params.id = entity.id
		controller.params.version = "0"
		controller.update()
		assertNotNull renderArgs.view
		assertEquals "detail", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.parceiroInstance
		assertEquals entity, renderArgs.model.parceiroInstance
	}

	void testUpdateSucesso(){
		def entity = createPersistedEntity()
		controller.params.id = entity.id
		controller.update();
		assertNotNull redirectArgs.action
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "detail", redirectArgs.action
	}

	void testUpdateErroValidacao(){
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
		controller.parceiroService = parceiroService.createMock()
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
