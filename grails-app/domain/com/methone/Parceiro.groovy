package com.methone

import com.methone.enumeration.Interesse

/**
 *  Entidade que representa um usuario no sistema.
 */
class Parceiro  {
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

	static hasOne = [ranking:Ranking]

	static hasMany = [projetos:Projeto, propostas:Proposta, pagamentos:Pagamento,
		atividadesProjeto:AtividadeProjeto
	]

	static constraints = {
		email(blank:false,unique:true,email:true)
		login(blank:false,unique:true)
		senha(blank:false,password:true)
		nome(blank:false)
		interesse(blank:false)
		ranking(nullable:true)
	}
}
