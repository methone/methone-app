package com.methone.support

import org.aspectj.weaver.ResolvedType.SuperClassWalker;

import grails.test.ControllerUnitTestCase

/**
 *
 * Classe de suporte para testes unitarios dos controllers de CRUD
 * gerados pelo grails
 *
 */
abstract class CrudControllerUnitTest extends ControllerUnitTestCase {
    // TODO com outras entidades no futuro...

	CrudControllerUnitTest(Class controllerClazz){
		super(controllerClazz)
	}

	protected final void setUp() {
		super.setUp()
		buildDomainMocks()
		controller.metaClass.message = {args -> println "${args}"}
	}

	protected final  void tearDown() {
		super.tearDown()
	}

	void testIndex() {
		mockParams()
		controller.index()
		assertEquals "list", redirectArgs.action
		assertNotNull redirectArgs.params
		assertEquals controller.params.size(), redirectArgs.params.size()
		assertEquals controller.params , redirectArgs.params
	}

	void testListParamMaxLimitValue(){
		controller.params.max = "101"
		controller.list()
		assertEquals 100, controller.params.max
	}

	void testListParamMaxWellDefinied(){
		mockParams()
		controller.params.max = "1"
		controller.list()
		assertEquals 1, controller.params.max
	}

	void testEmptyReturnList(){
		mockParams()
		def map = controller.list()
		assertNotNull map
		assertNotNull map."${entityInstanceName()}InstanceList"
		assertTrue map."${entityInstanceName()}InstanceList".isEmpty()
		assertNotNull map."${entityInstanceName()}InstanceTotal"
		assertEquals 0, map."${entityInstanceName()}InstanceTotal"
	}

	void testNotEmptyReturnList(){
		def entity = createPersistedEntity()
		mockParams()
		def map = controller.list()
		assertNotNull map
		assertNotNull map."${entityInstanceName()}InstanceList"
		assertFalse map."${entityInstanceName()}InstanceList".isEmpty()
		assertEquals entity, map."${entityInstanceName()}InstanceList".getAt(0)
		assertNotNull map."${entityInstanceName()}InstanceTotal"
		assertEquals 1, map."${entityInstanceName()}InstanceTotal"
	}

	void testCreate(){
		def map = controller.create()
		assertNotNull map
		assertEquals  1, map.size()
		assertNotNull map."${entityInstanceName()}Instance"
		assertEquals map."${entityInstanceName()}Instance".getClass(), entityClass()
		assertNull  map."${entityInstanceName()}Instance".version
		assertNull  map."${entityInstanceName()}Instance".id
	}

	void testSaveErro(){
		controller.save()
		assertEquals "create", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model."${entityInstanceName()}Instance"
		assertEquals renderArgs.model."${entityInstanceName()}Instance".getClass() , entityClass()
	}

	void testSaveSucesso(){
		mockParams()
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
		createPersistedEntity()
		mockParams()
		def map = controller.show()
		assertNotNull map
		assertNull redirectArgs.action
		assertEquals  1, map.size()
		assertNotNull map."${entityInstanceName()}Instance"
	}

	void testEditErro(){
		mockParams()
		def map = controller.edit()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "list", redirectArgs.action
	}

	void testEditSucesso(){
		createPersistedEntity()
		mockParams()
		def map = controller.edit()
		assertNotNull map
		assertNull redirectArgs.action
		assertEquals  1, map.size()
		assertNotNull map.especialidadeInstance
	}

	void testUpdateVersionInvalida(){
		def entity = createPersistedEntity()
		controller.params.id = entity.id
		controller.params.version = "0"
		controller.update()
		assertNotNull renderArgs.view
		assertEquals "edit", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model."${entityInstanceName()}Instance"
		assertEquals entity, renderArgs.model."${entityInstanceName()}Instance"
	}

	void testUpdateSucesso(){
		def entity = createPersistedEntity()
		controller.params.id = entity.id
		controller.update();
		assertNotNull redirectArgs.action
		assertNull renderArgs.view
		assertNull renderArgs.model
		assertEquals "show", redirectArgs.action
		assertEquals entity.id, redirectArgs.id
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
		assertEquals "edit", renderArgs.view
		assertNotNull renderArgs.model
		assertNotNull renderArgs.model."${entityInstanceName()}Instance"
		assertEquals entity, renderArgs.model."${entityInstanceName()}Instance"
	}

	void testUpdateErro(){
		def map = controller.update()
		assertNull map
		assertNotNull redirectArgs.action
		assertEquals "list", redirectArgs.action
	}

	//TODO test delete

	protected abstract String entityInstanceName()

	protected abstract Class entityClass()

	/**
	 *
	 * Metodo que realizara o mock das entidades de dominio
	 * envolvidades no controller
	 */
	protected abstract void buildDomainMocks()

	/**
	 *
	 * @return Retorna mapa com os parametros passados para o controller.
	 *
	 */
	protected abstract buildControllerParamsMock()

	/**
	 *
	 * @return retorna entidade ja persistida para os testes
	 */
	protected abstract createPersistedEntity()


	private mockParams(){
		def mockParams = buildControllerParamsMock()
		assertNotNull mockParams
		assertFalse mockParams.isEmpty()
		controller.params.putAll(mockParams)
		controller.params.id = "1"
		return mockParams
	}


}
