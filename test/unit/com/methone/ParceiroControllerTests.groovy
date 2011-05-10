package com.methone

import grails.test.*

import com.methone.enumeration.Interesse

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
		def paramsMock = [login : 'login', email : 'teste@teste.com', senha: "senha",
				nome:"nome", telefone: "telefone", endereco : "endereco",
				cep : "cep", estado: "estado", cidade : "cidade", interesse: "AMBOS"]
		controller.params.putAll(paramsMock)
		mockDomain(Parceiro)
		controller.metaClass.message = {args -> println "${args}"}
		controller.save()
		assertNotNull redirectArgs
		assertNotNull redirectArgs.action
		assertEquals "show", redirectArgs.action
		assertNotNull redirectArgs.id
	}

}
