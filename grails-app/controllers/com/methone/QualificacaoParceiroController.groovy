package com.methone

/**
 *
 * Controller para manipular as qualificacoes do parceiro
 *
 */
class QualificacaoParceiroController {
	static allowedMethods = [salvar: "POST"]

	def parceiroService
	def entityValidationService

	def qualificacao = {
		def parceiroInstance = parceiroService.getCurrentUser()
		return [parceiroInstance: parceiroInstance, areaList:Area.list(), especialidadeList:parceiroInstance.especialidades]
	}

	def salvar = {
		def parceiro = Parceiro.get(params.id)
		if(parceiro) {
			if(entityValidationService.validateVersion(parceiro, params.version)){
				parceiro.especialidades = getEspecialidadeFromResquest()
				parceiro.save()
				flash.message = "${message(code: 'salvoSucesso', args: [message(code: 'parceiro')])}"
			} else {
			  render(view: "qualificacao", model: [parceiroInstance: parceiro, areaList:Area.list(), especialidadeList:parceiro.especialidades])
			  return
			}
		} else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro')])}"
		}
		redirect(action: "qualificacao")
	}

	/**
	 *
	 * @return Retorna as especialidades que vieram do request (selecionadas na tela)
	 */
	private getEspecialidadeFromResquest() {
		def especialidadesSelecionadas = params.especialidade
		if(especialidadesSelecionadas != null && especialidadesSelecionadas.size() > 0) {
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
			return especialidadeList
		}
		return null
	}
}
