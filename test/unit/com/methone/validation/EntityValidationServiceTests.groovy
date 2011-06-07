package com.methone.validation

import grails.test.*

import com.methone.Parceiro

class EntityValidationServiceTests extends GrailsUnitTestCase {
	EntityValidationService entityValidationService

	protected void setUp() {
        super.setUp()
		mockDomain(Parceiro)
		entityValidationService = new EntityValidationService();
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testValidateVersionNula() {
		Parceiro p = new Parceiro();
		assertFalse entityValidationService.validateVersion(p,null)
		assertEquals 'Versao nula', 'versaoNula', p.errors['version']
    }

	void testValidateVersionInvalida() {
		Parceiro p = new Parceiro();
		p.version = 2
		assertFalse entityValidationService.validateVersion(p,1)
		assertEquals 'Versao invalida', 'default.optimistic.locking.failure', p.errors['version']
	}

	void testValidateVersionSucesso() {
		Parceiro p = new Parceiro();
		p.version = 1
		assertTrue entityValidationService.validateVersion(p,1)
		assertTrue entityValidationService.validateVersion(p,2)
	}

	void testValidateVersionEntidadeNula() {
		try {
			entityValidationService.validateVersion(null,1)
			fail()
		} catch (NullPointerException e){
			assertNotNull e
		}
	}

}
