package com.methone.model

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

	static constraints = {
		nome(blank:false)
	}
}
