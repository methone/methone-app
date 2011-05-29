package com.methone

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.*

import com.methone.enumeration.Interesse

class ParceiroServiceTests extends GrailsUnitTestCase {
	def springSecurityService
	ParceiroService parceiroService

	protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateErro() {
		buildMocks()
		assertFalse parceiroService.create(null)
		assertFalse parceiroService.create(new Parceiro())
    }

	void testCreateSucesso(){
		buildMocks()
		// parceiro a ser testado
		Parceiro parceiro = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)

		// mock do metodo do service
		springSecurityService.demand.encodePassword("senha") { ->
			return "senha"
		}
		springSecurityService.demand.reauthenticate("username","senha") { ->

		}
		assertTrue parceiroService.create(parceiro)

	}

	void testGetCurrentUserSucesso(){
		buildMocks()
		Parceiro p = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)

		p.save()
		springSecurityService.demand.getPrincipal() { ->
			return p
		}
		def user = parceiroService.getCurrentUser()
		assertNotNull user
		assertEquals p, user
	}

	private void buildMocks(){
		parceiroService = new ParceiroService()
		mockDomain(Parceiro)
		springSecurityService = mockFor(SpringSecurityService)
		parceiroService.springSecurityService = springSecurityService.createMock()
	}
}
