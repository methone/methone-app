package com.methone

class ParceiroController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [parceiroInstanceList: Parceiro.list(params), parceiroInstanceTotal: Parceiro.count()]
    }

    def create = {
        def parceiroInstance = new Parceiro()
        parceiroInstance.properties = params
        return [parceiroInstance: parceiroInstance]
    }

    def save = {
        def parceiroInstance = new Parceiro(params)
        if (parceiroInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), parceiroInstance.id])}"
            redirect(action: "show", id: parceiroInstance.id)
        }
        else {
            render(view: "create", model: [parceiroInstance: parceiroInstance])
        }
    }

    def show = {
        def parceiroInstance = Parceiro.get(params.id)
        if (!parceiroInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
            redirect(action: "list")
        }
        else {
            [parceiroInstance: parceiroInstance]
        }
    }

    def edit = {
        def parceiroInstance = Parceiro.get(params.id)
        if (!parceiroInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [parceiroInstance: parceiroInstance]
        }
    }

    def update = {
        def parceiroInstance = Parceiro.get(params.id)
        if (parceiroInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (parceiroInstance.version > version) {
                    
                    parceiroInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'parceiro.label', default: 'Parceiro')] as Object[], "Another user has updated this Parceiro while you were editing")
                    render(view: "edit", model: [parceiroInstance: parceiroInstance])
                    return
                }
            }
            parceiroInstance.properties = params
            if (!parceiroInstance.hasErrors() && parceiroInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), parceiroInstance.id])}"
                redirect(action: "show", id: parceiroInstance.id)
            }
            else {
                render(view: "edit", model: [parceiroInstance: parceiroInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def parceiroInstance = Parceiro.get(params.id)
        if (parceiroInstance) {
            try {
                parceiroInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'parceiro.label', default: 'Parceiro'), params.id])}"
            redirect(action: "list")
        }
    }
}
