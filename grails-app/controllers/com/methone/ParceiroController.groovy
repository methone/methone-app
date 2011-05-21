package com.methone


class ParceiroController {
	def parceiroService
    static allowedMethods = [save: "POST"]


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
