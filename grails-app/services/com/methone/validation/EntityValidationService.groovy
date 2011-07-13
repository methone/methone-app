package com.methone.validation

/**
 *
 * Validacoes aplicaveis a todas as entidades
 *
 */
class EntityValidationService {

    static transactional = false


	//TODO criar metodo que valida se o usuario logado eh o mesmo passado como parametro..

	/**
	 * Verifica se a versao da entidade eh a atual
	 *
	 * @param entity entidade a ser validada (tem que ser a entidade persistida no banco. Recomenda sempre fazer um get na entidade antes
	 * para assegurar que o metodo retornara uma resposta real sobre a versao do objeto).
	 * O parametro entity deve ser uma entidade do modelo.
	 *
	 * @param versionInUse versao que esta sendo usada
	 * @throws NullPointerException caso o argumento persistedEntity seja nulo.
	 * @return true caso a versao do objeto estaja ok. Caso contrario, false.
	 */
    public boolean validateVersion(def persistedEntity, def versionInUse) {
		if (versionInUse) {
			def version = versionInUse.toLong()
			if (persistedEntity.version > version) {
				// opcao por manter a chave da mensagem padrao default.optimistic.locking.failure
				// para nao ter problemas com codigos gerados pelo grails
				persistedEntity.errors.rejectValue("version", "default.optimistic.locking.failure")
				return false
			}
			return true
		}
		persistedEntity.errors.rejectValue("version", "versaoNula")
		// versao em uso nula.
		return false
    }
}
