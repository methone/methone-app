package com.methone.enumeration



import grails.test.*

class InteresseTests extends GrailsUnitTestCase {

	void testTamanhoEnum() {
		def interesseList = Interesse.values()
		assertNotNull (interesseList)
		assertEquals(3, interesseList.length)
	}

	void testBundleKey(){
		assertEquals "contratar" , Interesse.CONTRATAR.bundleKey;
		assertEquals "serContratado" , Interesse.SER_CONTRATADO.bundleKey;
		assertEquals "contratarSerContratado" , Interesse.AMBOS.bundleKey;
	}

}
