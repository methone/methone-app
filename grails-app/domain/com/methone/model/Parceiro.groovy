package com.methone.model

import com.methone.enumeration.Interesse;

/**
 *  Entidade que representa um usuario no sistema. 
 */
class Parceiro {
	String login
	String email
	String senha
	String nome
	String telefone
	String endereco
	String cep
	String estado
	String cidade
	Interesse interesse

	static constraints = {
		login(blank:false)
		email(blank:false,unique:true)
		senha(blank:false)
		nome(blank:false)
		interesse(blank:false)
	}
}
