package com.methone

import com.methone.enumeration.StatusAtividadeProjeto

/**
 * 
 * Atividades relativa a um projeto
 *
 */
class AtividadeProjeto {
	String nome
	String descricao
	Integer diasConclusao
	StatusAtividadeProjeto status
	
	static belongsTo = [parceiro:Parceiro]

	static constraints = {
		nome(blank:false)
	}
}
