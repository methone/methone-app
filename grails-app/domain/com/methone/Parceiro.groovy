package com.methone

import com.methone.authentication.User
import com.methone.enumeration.Interesse

/**
 *  Entidade que representa um usuario no sistema.
 */
class Parceiro extends User {

	String email
	String nome
	String telefone
	String endereco
	String cep
	String estado
	String cidade
	String empresa
	Interesse interesse
	String urlImagem


	static hasOne = [ranking:Ranking]

	static hasMany = [projetos:Projeto, propostas:Proposta, pagamentos:Pagamento,
		atividadesProjeto:AtividadeProjeto, especialidades:Especialidade
	]

	static constraints = {
		email(blank:false,unique:true,email:true)
		nome(blank:false)
		interesse(blank:false)
		ranking(nullable:true)
		empresa(nullable:true, blank:true)
		urlImagem(nullable:true, blank:true)
	}
}
