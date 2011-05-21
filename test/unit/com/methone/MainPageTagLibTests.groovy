package com.methone

import grails.test.*

class MainPageTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        // o setup da classe mae ja cria uma instancia mock da tagLib
		super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testRedirectMainPage() {
	   assertNotNull tagLib.response
	   assertNull tagLib.response.redirectedUrl
	   tagLib.redirectMainPage()
	   assertNotNull tagLib.response
	   assertNotNull tagLib.response.redirectedUrl
	   assertEquals "/principal/principal.gsp", tagLib.response.redirectedUrl

    }
}
