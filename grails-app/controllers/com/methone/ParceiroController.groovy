package com.methone

import com.methone.validation.EntityValidationService;

import grails.plugins.springsecurity.SpringSecurityService;

/**
 * Controller utilizado no cadastro de parceiro
 */
class ParceiroController {
	def parceiroService

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
}
