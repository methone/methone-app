package com.methone

import grails.test.*

class AreaEspeciadadeTagLibTests extends TagLibUnitTestCase {
    protected void setUp() {
        super.setUp()
		mockDomain(Area)
		mockDomain(Especialidade)
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testAreaEspecilidadeCheck() {
		Area area = new Area()
		area.descricao = "descricao"
		area.nome = "nome"
		area.save()	
		
		Especialidade especialidade = new Especialidade()
		especialidade.descricao = "descricao"
		especialidade.nome = "nome"
		especialidade.area = area
		especialidade.save()	
		
		def attrs = [area:area]
		
		tagLib.areaEspecilidadeCheck(attrs)
		assertNotNull tagLib.renderArgs
		assertNotNull tagLib.renderArgs.template
		assertNotNull tagLib.renderArgs.model
		assertNotNull tagLib.renderArgs.model.area
		assertNotNull tagLib.renderArgs.model.especialidades
		
		assertEquals "/templates/areaEspecialidadeTemplate", tagLib.renderArgs.template
		assertEquals area, tagLib.renderArgs.model.area
		def list = [especialidade]
		assertEquals list, tagLib.renderArgs.model.especialidades
		
    }
}
