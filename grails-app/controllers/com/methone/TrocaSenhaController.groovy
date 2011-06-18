package com.methone

class TrocaSenhaController {
	def parceiroService
	def springSecurityService
	def entityValidationService

    def index = {
		def parceiroInstance = parceiroService.getCurrentUser()
		return [parceiroInstance: parceiroInstance]
	}

	def salvar = {
		Parceiro parceiro = Parceiro.get(params.id)
		if(parceiro) {
			if(!entityValidationService.validateVersion(parceiro, params.version)) {
				render(view: "index", model: [parceiroInstance: parceiro])
				return
			}
			parceiro.email = params.email
			if(springSecurityService.encodePassword(params.senhaAtual) != parceiro.password){
				parceiro.errors.rejectValue "password", "senhaInvalida"
			}
			if(!params.novaSenha) {
				parceiro.errors.rejectValue "password", "novaSenhaVazia"
			}
			if(params.novaSenha != params.confirmaNovaSenha){
				parceiro.errors.rejectValue "password", "senhasNaoConferem"
			}
			if(!parceiro.hasErrors() && parceiro.validate()) {
				parceiro.password = springSecurityService.encodePassword(params.novaSenha)
				parceiro.save()
				flash.message = "${message(code: 'senhaAtualizada')}"
				redirect(action:"index")
			}
		}
		render(view: "index", model: [parceiroInstance:parceiro])
	}

}
