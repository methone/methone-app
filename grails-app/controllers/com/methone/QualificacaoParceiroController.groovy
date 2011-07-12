package com.methone

/**
 *
 * Controller para manipular as qualificacoes do parceiro
 *
 */
class QualificacaoParceiroController {
	static allowedMethods = [salvar: "POST"]

	def parceiroService

	def qualificacao = {
		def parceiroInstance =  parceiroService. getCurrentUser()
		def areaList = Area.list()
		def especialidadeList = parceiroInstance.especialidades
		return [parceiroInstance:parceiroInstance, areaList:areaList, especialidadeList:especialidadeList]
	}

	def salvar = {
		// pega as especialidades selecionadas na tela
		def especialidadesSelecionadas = params.especialidade
		if(especialidadesSelecionadas != null) {
			// TODO validar versao e parceiro nulo
			def parceiro = Parceiro.get(params.id)
			if(especialidadesSelecionadas.size() > 0){
				def especialidadeList = new ArrayList<Especialidade>()
				def especialidade = null
				for(especialidadeId in especialidadesSelecionadas) {
					if(especialidadeId != null){
						especialidade = Especialidade.get(especialidadeId)
						if(especialidade != null){
							especialidadeList.add(especialidade)
						}
					}
				}
				parceiro.especialidades = especialidadeList
			} else {
				parceiro.especialidades = null
			}
			parceiro.save()
		} else {
			// TODO exibir mensagem de erro
		}
		redirect(action: "qualificacao")
	}
}
