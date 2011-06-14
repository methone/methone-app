package com.methone

import grails.plugins.springsecurity.SpringSecurityService
import grails.test.*

import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile

import com.methone.enumeration.Interesse

class ParceiroServiceTests extends GrailsUnitTestCase {
	def springSecurityService
	def imageService
	ParceiroService parceiroService


	protected void setUp() {
        super.setUp()
		buildMocks()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testCreateErro() {

		assertFalse parceiroService.create(null)
		assertFalse parceiroService.create(new Parceiro())
    }

	void testCreateSucesso(){

		// parceiro a ser testado
		Parceiro parceiro = getParceiro()

		// mock do metodo do service
		springSecurityService.demand.encodePassword("senha") { ->
			return "senha"
		}
		springSecurityService.demand.reauthenticate("username","senha") { ->

		}
		assertTrue parceiroService.create(parceiro)

	}

	void testGetCurrentUserSucesso(){
		Parceiro p = getParceiro()
		p.save()
		springSecurityService.demand.getPrincipal() { ->
			return p
		}
		def user = parceiroService.getCurrentUser()
		assertNotNull user
		assertEquals p, user
	}

	void testUpdateDetalheParceiroInvalido(){
		assertFalse parceiroService.updateDetalheParceiro(null, null, null)
		Parceiro p = new Parceiro()
		p.id = null
		assertFalse parceiroService.updateDetalheParceiro(p, null, null)
		assertTrue p.errors.isEmpty()
		p.id = 1
		assertFalse parceiroService.updateDetalheParceiro(p, null, null)
		assertFalse p.errors.isEmpty()
		assertNull p.errors['urlImagem']

	}

	void testUpdateDetalheParceiroExtensaoInvalida() {
		imageService.demand.extensaoValida() { nomeImagem ->
			return false
		}
		Parceiro p = getParceiro()
		p.id = 1
		byte[] content = "teste".getBytes()
		MultipartFile multipartFile = new MockMultipartFile("nome", content)
		assertFalse parceiroService.updateDetalheParceiro(p, "nomeImagem", multipartFile)
		assertNotNull p.errors['urlImagem']
	}

	void testUpdateDetalheParceiroTamanhoImagemInvalida() {
		imageService.demand.extensaoValida() { nomeImagem ->
			return true
		}
		imageService.demand.tamanhoImagemValido() { imagem, tamanhoMax ->
			return false
		}
		Parceiro p = getParceiro()
		p.id = 1
		byte[] content = "teste".getBytes()
		MultipartFile multipartFile = new MockMultipartFile("nome", content)
		assertFalse parceiroService.updateDetalheParceiro(p, "nomeImagem", multipartFile)
		assertNotNull p.errors['urlImagem']
	}

	void testUpdateDetalheParceiroExtensaoSucesso() {
		imageService.demand.extensaoValida() { nomeImagem ->
			return true
		}
		imageService.demand.tamanhoImagemValido() { imagem, tamanhoMax ->
			return true
		}
		imageService.demand.deleteImage() { diretorio, nomeImagem ->

		}
		imageService.demand.saveImage() { diretorio, nomeImagem,imagem ->

	    }
		Parceiro p = getParceiro()
		p.id = 1
		p.urlImagem = "teste"
		byte[] content = "teste".getBytes()
		MultipartFile multipartFile = new MockMultipartFile("nome", content)
		assertTrue parceiroService.updateDetalheParceiro(p, "nomeImagem", multipartFile)
		assertTrue p.errors.isEmpty()
	}

	private void buildMocks(){
		parceiroService = new ParceiroService()
		mockDomain(Parceiro)
		springSecurityService = mockFor(SpringSecurityService)
		imageService = mockFor(ImageService)
		parceiroService.springSecurityService = springSecurityService.createMock()
		parceiroService.imageService = imageService.createMock()
	}

	private Parceiro getParceiro(){
		Parceiro p = new Parceiro(username : "username", email : "teste@teste.com", password: "senha",
			nome:"nome", telefone: "telefone", endereco : "endereco",
			cep : "cep", estado: "estado", cidade : "cidade", interesse: Interesse.AMBOS)
		return p
	}
}
