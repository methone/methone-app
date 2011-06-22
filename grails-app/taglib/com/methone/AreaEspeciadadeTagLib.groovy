package com.methone

import com.methone.Area
import com.methone.Especialidade;

class AreaEspeciadadeTagLib {

	def areaEspecilidadeCheck  = { attrs ->
		def especialidadeList = Especialidade.findAllByArea(attrs.area)		
		out << render(template:"/templates/areaEspecialidadeTemplate",
			model:[area:attrs.area, especialidades: especialidadeList, especialidadeSelecionadas:attrs.especialidadeSelecionadas])
	}
	
}
