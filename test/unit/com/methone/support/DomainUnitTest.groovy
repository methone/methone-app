package com.methone.support

import grails.test.GrailsUnitTestCase;

/**
 *
 * Classe de apoio para testes unitarios da camada de dominio
 *
 */
abstract class DomainUnitTest extends GrailsUnitTestCase {


	/**
	 * Testa se um campo da entidade esta em branco
	 * @param entity entidade a ser testada
	 * @param property propriedade que sera submetida a validacao
	 */
	void testBlank(def entity, def property){
		entity."$property" = null
		assertFalse entity.validate()
		assertEquals  'nullable', entity.errors[property]

		entity.properties[property] = ""
		assertFalse entity.validate()
		assertEquals 'blank', entity.errors[property]

		entity.properties[property]  = "  "
		assertFalse entity.validate()
		assertEquals 'blank', entity.errors[property]
	}
}
