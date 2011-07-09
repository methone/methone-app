package com.methone

/**
 * Controller para manipular os detalhes do parceiro
 */
class DetalheParceiroController {
	static allowedMethods = [update: "POST"]
	def parceiroService
    def entityValidationService

	def detail = {
		def parceiroInstance =  parceiroService. getCurrentUser()
		if(parceiroInstance){
			return [parceiroInstance:parceiroInstance, diretorioImagem : parceiroService.diretorioImagemRelativo]
		}
	}

	def update = {
		def parceiroInstance = Parceiro.get(params.id)
		if (parceiroInstance) {
			if(!entityValidationService.validateVersion(parceiroInstance, params.version)){
				render(view: "detail", model: [parceiroInstance: parceiroInstance, diretorioImagem : parceiroService.diretorioImagemRelativo])
				return
			}
			parceiroInstance.properties = params
			def nomeOriginal = null
			def file = null
			if(params.file){
				nomeOriginal = params.file.originalFilename
				file = request.getFile("file")
			}
			if (parceiroService.updateDetalheParceiro(parceiroInstance, nomeOriginal, file)) {
				flash.message = "${message(code: 'salvoSucesso', args: [message(code: 'parceiro')])}"
				redirect(action: "detail")
			} else {
			  render(view: "detail", model: [parceiroInstance: parceiroInstance, diretorioImagem : parceiroService.diretorioImagemRelativo])
			}
		} else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro')])}"
			redirect(action: "detail")
		}
	}

}
