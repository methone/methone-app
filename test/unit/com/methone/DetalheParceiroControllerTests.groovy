package com.methone

import grails.test.*

class DetalheParceiroControllerTests extends ControllerUnitTestCase {
	def parceiroService

	protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testDetail() {
		buildMocks()
		Parceiro p = new Parceiro()
		p.id = 1
		parceiroService.demand.getCurrentUser()  { ->
			return p
		}
		def map = controller.detail()
		assertNotNull map
		assertEquals  1, map.size()
		assertNotNull map.parceiroInstance
		assertTrue map.parceiroInstance instanceof Parceiro
		assertNotNull  map.parceiroInstance.id
    }

	private void buildMocks(){
		mockDomain(Parceiro)
		parceiroService = mockFor(ParceiroService)
		controller.parceiroService = parceiroService.createMock()
	}
}
