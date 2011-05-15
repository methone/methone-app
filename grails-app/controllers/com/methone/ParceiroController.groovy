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
        if (parceiroInstance.validate()) {
			parceiroInstance.password = springSecurityService.encodePassword(parceiroInstance.password)
			parceiroInstance.save(flush: true)
			flash.message = "${message(code: 'salvoSucesso', args: [message(code: 'parceiro', default: 'Parceiro')])}"
			// TODO enviar email para o usuario falando do login
			//redirect(uri:"/index.gsp")
			render(view: "create")
        } else {
            render(view: "create", model: [parceiroInstance: parceiroInstance])
        }
    }
}
