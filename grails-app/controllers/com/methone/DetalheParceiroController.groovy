package com.methone

/**
 * Controller para manipular os detalhes do parceiro
 */
class DetalheParceiroController {
	def parceiroService

	def detail = {
		def parceiroInstance =  parceiroService.getCurrentUser()
		return [parceiroInstance:parceiroInstance]
	}

	def update = {
		def parceiroInstance = Parceiro.get(params.id)
		if (parceiroInstance) {
			if (params.version) {
				def version = params.version.toLong()
				if (parceiroInstance.version > version) {
					parceiroInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'parceiro')] as Object[], "Another user has updated this Especialidade while you were editing")
					render(view: "detail", model: [parceiroInstance: parceiroInstance])
					return
				}
			}
			parceiroInstance.properties = params
			if (!parceiroInstance.hasErrors() && parceiroInstance.save(flush: true)) {
				flash.message = "${message(code: 'default.updated.message', args: [message(code: 'parceiro'), parceiroInstance.id])}"
				redirect(action: "detail", id: parceiroInstance.id)
			} else {
				render(view: "detail", model: [parceiroInstance: parceiroInstance])
			}
		} else {
			flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro'), params.id])}"
			redirect(action: "detail")
		}
	}
}
