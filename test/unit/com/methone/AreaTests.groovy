package com.methone

import com.methone.support.DomainUnitTest;

import grails.test.*


class AreaTests extends DomainUnitTest {

    def area

	private Area getAreaPreenchida(){
		return new Area(nome: "nome", descricao: "descricao")
	}

	protected void setUp() {
        super.setUp()
		area = getAreaPreenchida()
		mockForConstraintsTests (Area, [area])
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testSucesso() {
	   area.projetos = null
	   area.especialidades = null
	   assertTrue area.validate()
	   assertFalse area.hasErrors()
    }

	void testNome(){
		testBlank(area,'nome')
	}

	void testDescricao(){
		testBlank(area,'descricao')
	}
}
