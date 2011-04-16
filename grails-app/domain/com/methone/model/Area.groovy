package com.methone.model

import com.methone.enumeration.Interesse;

/**
 * Area de um projeto
 */
class Area {
	String nome
	String descricao

	static constraints = {
		nome(blank:false)
		descricao(blank:false)
	}
}
