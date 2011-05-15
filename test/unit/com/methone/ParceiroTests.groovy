package com.methone

import grails.test.*

import com.methone.enumeration.Interesse

class ParceiroTests extends GrailsUnitTestCase {
	def Parceiro parceiro

	Parceiro getParceiroPreenchido (){
		new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
				nome:"nome", telefone: "telefone", endereco : "endereco",
				cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)
	}

	protected void setUp() {
		super.setUp()
		// cria um parceiro totalmente preenchido
		parceiro = getParceiroPreenchido()
		// acrescenta o validate() para testar as constraits
		mockForConstraintsTests(Parceiro, [parceiro])

	}

	void testSucesso() {
		parceiro.ranking = null;
		assertTrue parceiro.validate()
		assertFalse parceiro.hasErrors()

		parceiro.ranking = new Ranking()
		assertTrue parceiro.validate()
		assertFalse parceiro.hasErrors()
	}

	void testEmail(){
		testBlank('email')

		// teste email unico
		def parceiroTest = getParceiroPreenchido()
		parceiro.email = "teste@teste.com";
		mockForConstraintsTests(Parceiro, [parceiroTest])

		assertFalse parceiro.validate()
		assertEquals 'Email ja existente', 'unique', parceiro.errors['email']
	}

	void testUsername(){
		testBlank('username')

		def parceiroTest = getParceiroPreenchido()
		parceiro.username = "username"
		mockForConstraintsTests(Parceiro, [parceiroTest])

		assertFalse parceiro.validate()
		assertEquals 'Username ja existente', 'unique', parceiro.errors['username']
	}

	void testPassword(){
		testBlank('password')
	}

	void testNome(){
		testBlank('nome')
	}

	void testInteresse(){
		parceiro.interesse = null
		assertFalse parceiro.validate()
		assertEquals 'Interesse nulo', 'nullable', parceiro.errors['interesse']
	}

	void testBlank(def property){
		parceiro."$property" = null
		assertFalse parceiro.validate()
		assertEquals  'nullable', parceiro.errors[property]

		parceiro.properties[property] = ""
		assertFalse parceiro.validate()
		assertEquals 'blank', parceiro.errors[property]

		parceiro.properties[property]  = "  "
		assertFalse parceiro.validate()
		assertEquals 'blank', parceiro.errors[property]
	}
}
