package com.methone

/**
 * Controller para manipular os detalhes do parceiro
 */
class DetalheParceiroController {
	static allowedMethods = [update: "POST"]
	def parceiroService
    def entityValidationService

	def detail = {
		def parceiroInstance =  parceiroService.getCurrentUser()
		if(parceiroInstance){
			return [parceiroInstance:parceiroInstance]
		}
	}

	def update = {
		def parceiroInstance = Parceiro.get(params.id)
		if (parceiroInstance) {
			if(!entityValidationService.validateVersion(parceiroInstance, params.version)){
				render(view: "detail", model: [parceiroInstance: parceiroInstance])
				return
			}
			parceiroInstance.properties = params
		    //TODO descomentar
			//	upload(parceiroInstance)
			if (!parceiroInstance.hasErrors() && parceiroInstance.save(flush: true)) {
				flash.message = "${message(code: 'salvoSucesso', args: [message(code: 'parceiro')])}"
				redirect(action: "detail")
			} else {
			  render(view: "detail", model: [parceiroInstance: parceiroInstance])
			}
		} else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro')])}"
			redirect(action: "detail")
		}
	}

	// TODO verificar POST
	def upload = { parceiroInstance ->
		def nomeOriginal = params.file.originalFilename
		def f = request.getFile("file")
		if(!f.empty){
			// TODO url devera ser externa a aplicacao (vinda de um arquivo de propriedades)
			f.transferTo(new File("web-app/images/uploads/${nomeOriginal}"))
			// TODO a url da imagem nao pode ser o nome original da imagem
			parceiroInstance.urlImagem = nomeOriginal
		} else {
			// TODO mensagem do bundle...
			flash.message = "nao foi possível transferir o arquivo"
		}
	}
}
