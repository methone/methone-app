package com.methone

/**
 *
 * Especialidade de uma area ou projeto
 *
 */
class Especialidade {

	String nome
	String descricao

	static belongsTo = [area:Area]

	static constraints = {
		nome(blank:false)
		descricao(blank:false, widget:'textarea')
	}

	String toString(){
		descricao
	}
}
