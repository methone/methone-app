package com.methone.model

/**
 * 
 * Especialidade de uma area ou projeto
 *
 */
class Especialidade {
	
	String nome
	String descricao

	static constraints = {
		nome(blank:false)
		descricao(blank:false)
	}
}
