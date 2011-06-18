package com.methone

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.*

import com.methone.enumeration.Interesse
import com.methone.validation.EntityValidationService
import com.sun.org.apache.xalan.internal.xsltc.compiler.ForEach;

class TrocaSenhaControllerTests extends ControllerUnitTestCase {
	def parceiroService
	def entityValidationService
	def springSecurityService

	protected void setUp() {
        super.setUp()
		buildMocks()
		controller.metaClass.message = {args -> "${args}"}
    }

    protected void tearDown() {
        super.tearDown()
    }

	void testIndex() {
		Parceiro p = new Parceiro(id: 1)

		parceiroService.demand.getCurrentUser() { ->
			return p
		}
		def map = controller.index()
		assertNotNull map
		assertNotNull map.parceiroInstance
		assertEquals p, map.parceiroInstance
	}

	void testSenhaIncorreta() {
		springSecurityService.demand.encodePassword() {password ->
			return "senhaErrada"
		}
		Parceiro p = createPersistedEntity()
		controller.params.id = p.id
		controller.params.senhaAtual = "senha2"
		controller.params.novaSenha = "senha"
		controller.params.confirmaNovaSenha = "senha"
		controller.params.email = ""
		controller.salvar()
		assertNotNull controller.renderArgs.model.parceiroInstance
		assertNotNull controller.renderArgs.model.parceiroInstance.errors['password']
		int count = 0
		for (erro in controller.renderArgs.model.parceiroInstance.errors) {
			count++
		}
		assertEquals 1, count
	}


	void testSenhasDiferentes() {
		springSecurityService.demand.encodePassword() {password ->
			return "senhaErrada"
		}
		Parceiro p = createPersistedEntity()
		controller.params.id = p.id
		controller.params.senhaAtual = "senha"
		controller.params.novaSenha = "senha"
		controller.params.confirmaNovaSenha = "senha2"
		controller.params.email = ""
		controller.salvar()
		assertNotNull controller.renderArgs.model.parceiroInstance
		assertNotNull controller.renderArgs.model.parceiroInstance.errors['password']
		int count = 0
		for (erro in controller.renderArgs.model.parceiroInstance.errors) {
			count++
		}
		assertEquals 1, count
	}

	void testNovaSenhaNula() {
		springSecurityService.demand.encodePassword() {password ->
			return "senhaErrada"
		}
		Parceiro p = createPersistedEntity()
		controller.params.id = p.id
		controller.params.senhaAtual = "senha"
		controller.params.novaSenha = ""
		controller.params.confirmaNovaSenha = ""
		controller.params.email = ""
		controller.salvar()
		assertNotNull controller.renderArgs.model.parceiroInstance
		assertNotNull controller.renderArgs.model.parceiroInstance.errors['password']
		int count = 0
		for (erro in controller.renderArgs.model.parceiroInstance.errors) {
			count++
		}
		assertEquals 1, count
	}


	private void buildMocks(){
		mockDomain(Parceiro)
		parceiroService = mockFor(ParceiroService)
		entityValidationService = mockFor(EntityValidationService)
		springSecurityService = mockFor(SpringSecurityService)
		controller.entityValidationService = entityValidationService.createMock()
		controller.parceiroService = parceiroService.createMock()
		controller.springSecurityService = springSecurityService.createMock()
		entityValidationService.demand.validateVersion()  { entity,versionInUse->
			return true
		}
	}

	private createPersistedEntity(){
		Parceiro p = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS, version : 1)
		p.save()
		return p;
	}
}
