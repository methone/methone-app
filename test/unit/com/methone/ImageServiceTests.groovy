package com.methone

import grails.test.*

class ImageServiceTests extends GrailsUnitTestCase {
    ImageService imageService

	protected void setUp() {
        super.setUp()
		imageService = new ImageService()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testGetExtensao() {
		assertEquals "", imageService.getExtensao(null)
		assertEquals "", imageService.getExtensao("")
		assertEquals "", imageService.getExtensao("teste")
		assertEquals "", imageService.getExtensao("teste.")
		assertEquals "ext", imageService.getExtensao("teste.ext")
		assertEquals "ext", imageService.getExtensao("teste.ExT")
		assertEquals "ext2", imageService.getExtensao("teste.ExT.EXT2")
		assertEquals "ext3", imageService.getExtensao("teste.ExT.EXT2.eXt3")
		assertEquals "teste", imageService.getExtensao(".teste")
    }

	void testExtensaoValida(){
		assertTrue imageService.extensaoValida("teste.gif")
		assertTrue imageService.extensaoValida("teste.GIF")
		assertTrue imageService.extensaoValida("teste.GIf")
		assertTrue imageService.extensaoValida("teste.gIf")
		assertTrue imageService.extensaoValida("teste.giF")
		assertTrue imageService.extensaoValida("teste.jpg")
		assertTrue imageService.extensaoValida("teste.JPG")
		assertTrue imageService.extensaoValida("teste.jpeg")
		assertTrue imageService.extensaoValida("teste.JPEG")
		assertTrue imageService.extensaoValida("teste.png")
		assertTrue imageService.extensaoValida("teste.PNG")
		assertFalse imageService.extensaoValida("teste.bmp")
		assertFalse imageService.extensaoValida("teste.BMP")
		assertFalse imageService.extensaoValida("teste.FlashPix")
		assertFalse imageService.extensaoValida("teste.PNM")
		assertFalse imageService.extensaoValida("teste.pnm")
		assertFalse imageService.extensaoValida("teste.tiff")
		assertFalse imageService.extensaoValida("teste.TIFF")
		assertFalse imageService.extensaoValida("teste.WBMP")
		assertFalse imageService.extensaoValida("teste.wbmp")
		assertFalse imageService.extensaoValida("teste")
		assertFalse imageService.extensaoValida("")
		assertFalse imageService.extensaoValida(null)
	}
}
