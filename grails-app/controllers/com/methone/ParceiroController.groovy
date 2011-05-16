package com.methone


class ParceiroController {
	def springSecurityService
    static allowedMethods = [save: "POST"]


    def create = {
        def parceiroInstance = new Parceiro()
        return [parceiroInstance: parceiroInstance]
    }

    def save = {
        def parceiroInstance = new Parceiro(params)
        // TODO criar um servico que encapsula essa logica
		// TODO criar um servico de email para notificar o usuario
		if (parceiroInstance.validate()) {
			def password = parceiroInstance.password
			parceiroInstance.password = springSecurityService.encodePassword(parceiroInstance.password)
			parceiroInstance.save(flush: true)
			springSecurityService.reauthenticate(parceiroInstance.username, password)
			redirect(uri:"/principal/principal.gsp")
        } else {
            render(view: "create", model: [parceiroInstance: parceiroInstance])
        }
    }
}
