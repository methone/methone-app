package com.methone

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

	static hasMany = [especialidades:Especialidade]
	static hasOne = [area:Area]
	static belongsTo = [parceiro:Parceiro]

	static constraints = {
		nome(blank:false)
		descricao(blank:false)
	}
}
