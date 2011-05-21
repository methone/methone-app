package com.methone

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.*

import com.methone.enumeration.Interesse

class ParceiroServiceTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateErro() {
		ParceiroService parceiroService = new ParceiroService()
		mockDomain(Parceiro)
		assertFalse parceiroService.create(null)
		assertFalse parceiroService.create(new Parceiro())
    }

	void testCreateSucesso(){
		ParceiroService parceiroService = new ParceiroService()
		mockDomain(Parceiro)

		// parceiro a ser testado
		Parceiro parceiro = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)

		// mock do service do spring security
		def springSecurityService = mockFor(SpringSecurityService)
		// mock do metodo do service
		springSecurityService.demand.encodePassword("senha") {
			return "senha"
		}
		springSecurityService.demand.reauthenticate("username","senha") {

		}
		// atribui ao controller o mock
		parceiroService.springSecurityService = springSecurityService.createMock()

		assertTrue parceiroService.create(parceiro)

	}
}
