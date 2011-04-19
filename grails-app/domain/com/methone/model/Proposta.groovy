package com.methone.model

import com.methone.enumeration.StatusProposta

/**
 * 
 * Proposta feita a um projeto
 *
 */
class Proposta {
	BigDecimal valor
	Integer diasConclusao
	String descricao
	StatusProposta status
	
	static belongsTo = [parceiro:Parceiro]

	static constraints = {
		valor(blank:false)
		diasConclusao(blank:false)
	}
}
