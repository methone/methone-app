package com.methone.model

import com.methone.enumeration.StatusProjeto

/**
 * 
 * Projeto cadastrado por um parceiro.
 *
 */
class Projeto {
	String nome
	String descricao
	BigDecimal custoMinimo
	BigDecimal custoMaximo
	Integer diasProposta
	Integer diasConclusao // em dias
	StatusProjeto status


	static constraints = {
		nome(blank:false)
		descricao(blank:false)
	}
}
