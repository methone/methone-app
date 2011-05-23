package com.methone

import grails.test.*


class EspecialidadeControllerTests extends ControllerUnitTestCase {
	protected void setUp() {
		super.setUp()
		mockDomain(Especialidade)
		mockDomain(Area)
		controller.metaClass.message = {args -> println "${args}"}
	}

	protected void tearDown() {
		super.tearDown()
	}

	private void mockParams(def maxParam = "1"){
		def paramsMock = ["nome": "nome", "descricao" : "descricao", "max" : maxParam, "id" : "1"]
		controller.params.putAll(paramsMock)
	}

	private Especialidade createEspecialidade(){
		Area area = new Area(nome: "nome", descricao: "descricao")
		area.save()
		Especialidade especialidade = new Especialidade(nome: "nome", descricao: "descricao", area : area, version: 1)
		especialidade.save()
		return especialidade
	}

	void testIndex() {
		mockParams()
		controller.index()
		assertEquals "list", redirectArgs.action
		assertNotNull redirectArgs.params
		assertEquals "nome", redirectArgs.params.nome
		assertEquals "descricao", redirectArgs.params.descricao
	}

	void testListParamMaxLimitValue(){
		mockParams("101")
		controller.list()
		assertEquals 100, controller.params.max
	}

	void testListParamMaxWellDefinied(){
		mockParams()
		controller.list()
		assertEquals 1, controller.params.max
	}

	void testEmptyReturnList(){
		mockParams()
		def map = controller.list()
		assertNotNull map
		assertNotNull map.especialidadeInstanceList
		assertTrue map.especialidadeInstanceList.isEmpty()
		assertNotNull map.especialidadeInstanceTotal
		assertEquals 0, map.especialidadeInstanceTotal
	}

	void testNotEmptyReturnList(){
		Especialidade especialidade = createEspecialidade()
		mockParams()
		def map = controller.list()
		assertNotNull map
		assertNotNull map.especialidadeInstanceList
		assertFalse map.especialidadeInstanceList.isEmpty()
		assertEquals especialidade, map.especialidadeInstanceList.getAt(0)
		assertNotNull map.especialidadeInstanceTotal
		assertEquals 1, map.especialidadeInstanceTotal
	}


	void testCreate(){
		def map = controller.create()
		assertNotNull map
		assertEquals  1, map.size()
		assertNotNull map.especialidadeInstance
		assertTrue map.especialidadeInstance instanceof Especialidade
		assertNull  map.especialidadeInstance.version
		assertNull  map.especialidadeInstance.id
	}


	void testSaveErro(){
		mockParams()
		controller.save()
		assertEquals "create", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.especialidadeInstance
		assertTrue renderArgs.model.especialidadeInstance instanceof Especialidade
	}

	void testSaveSucesso(){
		mockParams()
		// coloca parametros extras para passar na validacao
		def paramsMock = ["area.id": "1", "area":"['id':'1']"]
		controller.params.putAll(paramsMock)
		controller.save()
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "show", redirectArgs.action
		assertEquals 1, redirectArgs.id
	}

	void testShowErro(){
		mockParams()
		def map = controller.show()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "list", redirectArgs.action
	}

	void testShowSucesso(){
		createEspecialidade();
		mockParams()
		def map = controller.show()
		assertNotNull map
		assertNull redirectArgs.action
		assertEquals  1, map.size()
		assertNotNull map.especialidadeInstance
	}

	void testEditErro(){
		mockParams()
		def map = controller.edit()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "list", redirectArgs.action
	}

	void testEditSucesso(){
		createEspecialidade();
		mockParams()
		def map = controller.edit()
		assertNotNull map
		assertNull redirectArgs.action
		assertEquals  1, map.size()
		assertNotNull map.especialidadeInstance
	}

	void testUpdateVersionInvalida(){
		def especialidade = createEspecialidade();
		mockParams()
		def paramsMock = ["version": "0"]
	    controller.params.putAll(paramsMock)
		controller.update()
		assertNotNull renderArgs.view
		assertEquals "edit", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.especialidadeInstance
		assertEquals especialidade, renderArgs.model.especialidadeInstance
	}

	void testUpdateSucesso(){
		createEspecialidade()
		mockParams()
		controller.update();
		assertNotNull redirectArgs.action
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "show", redirectArgs.action
		assertEquals 1, redirectArgs.id
	}

	void testUpdateErroValidacao(){
		def especialidade = createEspecialidade()
		def paramsMock = ["nome": "", "descricao" : "", "id" : "1"]
		controller.params.putAll(paramsMock)
		controller.update();
		assertNotNull renderArgs.view
		assertEquals "edit", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model.especialidadeInstance
		assertEquals especialidade, renderArgs.model.especialidadeInstance
	}


	void testUpdateErro(){
		def map = controller.update()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "list", redirectArgs.action
	}

	//TODO test delete
}
