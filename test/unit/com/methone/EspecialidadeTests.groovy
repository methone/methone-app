package com.methone

import com.methone.support.DomainUnitTest;

import grails.test.*

class EspecialidadeTests extends DomainUnitTest {

	def especialidade

	private Especialidade getEspecialidadePreenchida(){
		Area area = new Area(nome: "nome", descricao: "descricao")
		return new Especialidade(nome: "nome", descricao: "descricao", area : area)
	}

	protected void setUp() {
		super.setUp()
		especialidade = getEspecialidadePreenchida()
		mockForConstraintsTests (Especialidade, [especialidade])
	}

	protected void tearDown() {
		super.tearDown()
	}

	void testSucesso() {
		assertTrue especialidade.validate()
		assertFalse especialidade.hasErrors()
	}

	void testArea(){
		especialidade.area = null
		assertFalse especialidade.validate()
		assertTrue especialidade.hasErrors()
	}

	void testNome(){
		testBlank(especialidade,'nome')
	}

	void testDescricao(){
		testBlank(especialidade,'descricao')
	}

	void testToString(){
		assertEquals especialidade.descricao, especialidade.toString()
	}
}
