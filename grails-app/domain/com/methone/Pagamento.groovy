package com.methone

import com.methone.enumeration.StatusPagamento
import com.methone.enumeration.TipoPagamento

/**
 * 
 * Pagamento
 *
 */
class Pagamento {
	BigDecimal valor
	String descricao
	TipoPagamento tipo
	StatusPagamento status

	static belongsTo = [parceiro:Parceiro]

	static constraints = { 
		valor(blank:false)     
	}
}
