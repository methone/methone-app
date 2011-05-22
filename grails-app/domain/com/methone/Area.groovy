package com.methone

import com.methone.enumeration.Interesse;

/**
 * Area de um projeto
 */
class Area {
	String nome
	String descricao

	static hasMany = [especialidades:Especialidade, projetos:Projeto]

	static constraints = {
		nome(blank:false)
		descricao(blank:false, widget:'textarea')
	}

	String toString(){
		descricao
	}
}
