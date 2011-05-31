package com.methone

import grails.plugins.springsecurity.SpringSecurityService;

/**
 * Controller utilizado no cadastro de parceiro
 */
class ParceiroController {
	def parceiroService
	def springSecurityService
    static allowedMethods = [save: "POST", savePasswordEmail: "POST"]


    def create = {
	    def parceiroInstance = new Parceiro()
        return [parceiroInstance: parceiroInstance]
    }

    def save = {
        def parceiroInstance = new Parceiro(params)
		if (parceiroService.create(parceiroInstance)) {
			redirect(uri:"/principal/principal.gsp")
        } else {
            render(view: "create", model: [parceiroInstance: parceiroInstance])
        }
    }

	def changePasswordEmail = {
		def parceiroInstance = parceiroService.getCurrentUser()
		return [parceiroInstance: parceiroInstance]
	}

	def savePasswordEmail = {
		Parceiro parceiro = Parceiro.get(params.id)
		if(parceiro) {
			if (params.version) {
				def version = params.version.toLong()
				if (parceiro.version > version) {
					parceiro.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'parceiro')] as Object[], null)
					render(view: "changePasswordEmail", model: [parceiroInstance: parceiro])
					return
				}
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
				redirect(action:"changePasswordEmail")
			}
		}
		render(view: "changePasswordEmail", model: [parceiroInstance:parceiro])
	}
}
