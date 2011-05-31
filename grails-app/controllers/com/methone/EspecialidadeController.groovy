package com.methone

class EspecialidadeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [especialidadeInstanceList: Especialidade.list(params), especialidadeInstanceTotal: Especialidade.count()]
    }

    def create = {
        def especialidadeInstance = new Especialidade()
        especialidadeInstance.properties = params
        return [especialidadeInstance: especialidadeInstance]
    }

    def save = {
        def especialidadeInstance = new Especialidade(params)
        if (especialidadeInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), especialidadeInstance.id])}"
            redirect(action: "show", id: especialidadeInstance.id)
        }
        else {
            render(view: "create", model: [especialidadeInstance: especialidadeInstance])
        }
    }

    def show = {
        def especialidadeInstance = Especialidade.get(params.id)
        if (!especialidadeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
            redirect(action: "list")
        }
        else {
            [especialidadeInstance: especialidadeInstance]
        }
    }

    def edit = {
        def especialidadeInstance = Especialidade.get(params.id)
        if (!especialidadeInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [especialidadeInstance: especialidadeInstance]
        }
    }

    def update = {
        def especialidadeInstance = Especialidade.get(params.id)
        if (especialidadeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (especialidadeInstance.version > version) {
                    especialidadeInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'especialidade.label', default: 'Especialidade')] as Object[], "Another user has updated this Especialidade while you were editing")
                    render(view: "edit", model: [especialidadeInstance: especialidadeInstance])
                    return
                }
            }
            especialidadeInstance.properties = params
            if (!especialidadeInstance.hasErrors() && especialidadeInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), especialidadeInstance.id])}"
                redirect(action: "show", id: especialidadeInstance.id)
            }
            else {
                render(view: "edit", model: [especialidadeInstance: especialidadeInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def especialidadeInstance = Especialidade.get(params.id)
        if (especialidadeInstance) {
            try {
                especialidadeInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'especialidade.label', default: 'Especialidade'), params.id])}"
            redirect(action: "list")
        }
    }
}
