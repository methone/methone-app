package com.methone

import grails.test.GrailsUnitTestCase

import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.multipart.MultipartFile


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

	void testSaveImageNull(){
		try {
			imageService.saveImage(null, null, null)
		} catch (IOException e) {
			fail()
		}
	}

	void testSaveImageSucesso(){
		try {
			MultipartFile multipartFile = getMockMultipartFile(getAbsolutePath("/com/methone/resources/methone.jpg"));
			assertNotNull multipartFile
			imageService.saveImage(getAbsolutePath("/com/methone/resources/"), "methone_test.jpg", multipartFile)
			// verifica se o arquivo foi salvo corretamente
			File file = new File(getAbsolutePath("/com/methone/resources/methone_test.jpg"))
			assertNotNull file
			assertNotNull file.length()
			assertTrue file.length() > 0
			// deleta o arquivo depois do teste
			file.delete()
		} catch (IOException e) {
			fail()
		}
	}

	void testSaveImageDiretorioNaoExistente(){
		try {
			MultipartFile multipartFile = getMockMultipartFile(getAbsolutePath("/com/methone/resources/methone.jpg"));
			assertNotNull multipartFile
			imageService.saveImage("diretorioNaoExistente", "methone_test.jpg", multipartFile)
		} catch (IOException e) {
			assertNotNull e
		}
	}

	void testDeleteImageDiretorioNaoExistente(){
		try {
			imageService.deleteImage("diretorioNaoExistente", "methone_test.jpg")
		} catch (IOException e) {
			assertNotNull e
		}
	}

	void testDeleteImageSucesso(){
		try {
			MultipartFile multipartFile = getMockMultipartFile(getAbsolutePath("/com/methone/resources/methone.jpg"));
			assertNotNull multipartFile
			multipartFile.transferTo(new File(getAbsolutePath("/com/methone/resources/") + "methone_test.jpg"))
			imageService.deleteImage(getAbsolutePath("/com/methone/resources/"), "methone_test.jpg")
		} catch (IOException e) {
			fail()
		}
	}

	void testTamanhoImagemValido(){
		assertFalse imageService.tamanhoImagemValido(null, 0)
		MultipartFile multipartFile = getMockMultipartFile(getAbsolutePath("/com/methone/resources/methone.jpg"));
		assertNotNull multipartFile

		assertFalse imageService.tamanhoImagemValido(multipartFile, 0)
		assertTrue imageService.tamanhoImagemValido(multipartFile, 10000000000000)
	}

	private MultipartFile getMockMultipartFile(String path) {
		String contentType  = "image/jpeg"
		File file = new File(path)
		InputStream inputStream
		try {
			inputStream = new FileInputStream(file)
		} catch (FileNotFoundException e) {
			return null
		}
		MultipartFile multipartFile
		try {
			multipartFile = new MockMultipartFile(file.getName(), file.getAbsolutePath(), contentType, inputStream)
		} catch (IOException e) {
			return null;
		}
		return multipartFile
	}

	private String getAbsolutePath(String relativePath) {
		URL resource = getClass().getResource(relativePath)
		String path = resource != null ? resource.getPath() : ""
		path = path.replaceAll("file:/", "")
		if ("\\" == File.separator) {
			path = path.replaceAll("/", File.separator + File.separator)
		}
		return path.replaceAll("%20", " ")
	}

}
