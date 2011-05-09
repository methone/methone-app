package com.methone

import grails.test.*

class ParceiroControllerTests extends ControllerUnitTestCase {

	protected void setUp() {
		super.setUp()
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testCreate() {
		Map myParams = [login : 'login', email : 'teste@teste.com']
        controller.params.putAll(myParams)
		def map = controller.create()
		assertNotNull map
	}
}
