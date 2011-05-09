package com.methone

import grails.test.*

import com.methone.enumeration.Interesse

class ParceiroTests extends GrailsUnitTestCase {
	def Parceiro parceiro

	Parceiro getParceiroPreenchido (){
		new Parceiro(login : "login", email : "teste@teste.com", senha: "senha",
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

	void testLogin(){
		testBlank('login')

		def parceiroTest = getParceiroPreenchido()
		parceiro.login = "login"
		mockForConstraintsTests(Parceiro, [parceiroTest])

		assertFalse parceiro.validate()
		assertEquals 'Login ja existente', 'unique', parceiro.errors['login']
	}

	void testSenha(){
		testBlank('senha')
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
