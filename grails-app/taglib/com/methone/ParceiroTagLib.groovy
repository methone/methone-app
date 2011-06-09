package com.methone

/**
 *
 * TagLib para facilitar a montagem de telas de parceiro
 *
 */
class ParceiroTagLib {

	def formularioParceiro  = { attrs ->
		out << render(template:"/templates/parceiroTemplate",
			model:[parceiroInstance:attrs.parceiroInstance, action:attrs.action,
				cadastro:attrs.cadastro, message: attrs.message, enctype : attrs.enctype])
	}
}
