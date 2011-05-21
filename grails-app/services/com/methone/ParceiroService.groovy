package com.methone

import com.methone.Parceiro

/**
 *
 * Regras de negocio de parceiro
 *
 */
class ParceiroService {
    static transactional = true

	def springSecurityService

	/**
	 * Cria novo parceiro e autentica o mesmo no sistema.
	 * Metodo usado durante o cadastro de parceiro
	 * @param parceiro parceiro a ser salvo
	 * @return Retorna true caso o parceiro seja salvo com sucesso. Caso contrario false.
	 */
    public boolean create(Parceiro parceiro) {
		// TODO criar um servico de email para notificar o usuario
		if (parceiro != null && parceiro.validate()) {
			String password = parceiro.password
			parceiro.password = springSecurityService.encodePassword(parceiro.password)
			parceiro.save(flush: true)
			springSecurityService.reauthenticate(parceiro.username, password)
			return true
		}
		return false
    }
}
