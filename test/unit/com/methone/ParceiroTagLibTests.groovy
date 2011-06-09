package com.methone

import grails.test.*

class ParceiroTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testFormularioParceiro() {
		Parceiro p = new Parceiro();
		p.id = 1
		def attrs = [parceiroInstance:p, action:"save", cadastro : true, message : "message", enctype : "enctype"]
		tagLib.formularioParceiro(attrs)
		assertNotNull tagLib.renderArgs
		assertNotNull tagLib.renderArgs.template
		assertNotNull tagLib.renderArgs.model
		assertNotNull tagLib.renderArgs.model.parceiroInstance
		assertNotNull tagLib.renderArgs.model.action
		assertNotNull tagLib.renderArgs.model.cadastro
		assertNotNull tagLib.renderArgs.model.message
		assertEquals "/templates/parceiroTemplate", tagLib.renderArgs.template
		assertEquals p, tagLib.renderArgs.model.parceiroInstance
		assertEquals "save", tagLib.renderArgs.model.action
		assertTrue tagLib.renderArgs.model.cadastro
		assertEquals "message", tagLib.renderArgs.model.message
		assertEquals "enctype", tagLib.renderArgs.model.enctype

    }
}

