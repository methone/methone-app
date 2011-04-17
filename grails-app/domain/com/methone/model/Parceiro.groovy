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

	static hasMany = [projetos:Projeto, propostas:Proposta, pagamentos:Pagamento,
		atividadesProjeto:AtividadeProjeto
	]

	static hasOne = [ranking:Ranking]

	static constraints = {
		login(blank:false,unique:true)
		email(blank:false,unique:true,email:true)
		senha(blank:false,password:true)
		nome(blank:false)
		interesse(blank:false)
	}
}
