package com.methone


class ParceiroController {

    static allowedMethods = [save: "POST"]


    def create = {
        def parceiroInstance = new Parceiro()
        return [parceiroInstance: parceiroInstance]
    }

    def save = {
        def parceiroInstance = new Parceiro(params)
        if (parceiroInstance.save(flush: true)) {
            flash.message = "${message(code: 'salvoSucesso', args: [message(code: 'parceiro', default: 'Parceiro')])}"
			render(view: "create")
        } else {
            render(view: "create", model: [parceiroInstance: parceiroInstance])
        }
    }
}
