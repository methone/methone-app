package com.methone

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.*

class ParceiroControllerTests extends ControllerUnitTestCase {

	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testCreate() {
		def map = controller.create()
		assertNotNull map
		assertEquals  1, map.size()
		assertNotNull map.parceiroInstance
		assertTrue map.parceiroInstance instanceof Parceiro
		assertNull  map.parceiroInstance.version
		assertNull  map.parceiroInstance.id
	}

	void testSaveErro(){
		// mock das instancias de parceiro.
		// adiciona os metodos gerados dinamicamente
		mockDomain(Parceiro)
		controller.save()
		assertEquals "create", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.parceiroInstance
		assertTrue renderArgs.model.parceiroInstance instanceof Parceiro

	}

	void testSaveSucesso(){
		//  mocks do params para instanciar o parceiro no save
		def paramsMock = [username : 'username', email : 'teste@teste.com', password: "senha",
				nome:"nome", telefone: "telefone", endereco : "endereco",
				cep : "cep", estado: "estado", cidade : "cidade", interesse: "AMBOS"]
		controller.params.putAll(paramsMock)
		mockDomain(Parceiro)

		// mock do service do spring security
		def springSecurityService = mockFor(SpringSecurityService)
		// mock do metodo do service
		springSecurityService.demand.encodePassword("senha") {
			return "senha"
		}
		springSecurityService.demand.reauthenticate("username","senha") {

		}
		// atribui ao controller o mock
        controller.springSecurityService = springSecurityService.createMock()

		controller.metaClass.message = {args -> println "${args}"}
		controller.save()
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "/principal/principal.gsp", redirectArgs.uri

	//	springSecurityService.verify()
	}

}
