package com.methone

/**
 *
 * Controller para manipular as qualificacoes do parceiro
 *
 */
class QualificacaoParceiroController {
	def parceiroService

    def qualificacao = {
		def parceiroInstance =  parceiroService. getCurrentUser()
		def areaList = Area.list()
		return [parceiroInstance:parceiroInstance, areaList:areaList]
	}
}
