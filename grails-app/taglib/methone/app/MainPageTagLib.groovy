package methone.app

/**
 *  Componemte que redireciona para a pagina principal
 *  Para usar basta adiciona <g:redirectMainPage/> no gsp
 *
 */
class MainPageTagLib {
	def redirectMainPage = {
		response.sendRedirect("${request.contextPath}/principal/principal.gsp")
	}
}
