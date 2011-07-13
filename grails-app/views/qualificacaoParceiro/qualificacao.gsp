<%@ page import="com.methone.Parceiro" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>
          <g:message code="editeDetalhesPerfil"  />
        </title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="editarQualificacao" /></h1>
            <g:if test="${flash.message}">
    			<div class="message">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${parceiroInstance}">
    			<div class="errors">
        			<g:renderErrors bean="${parceiroInstance}" as="list" />
    			</div>
			</g:hasErrors>
            <div class="dialog">
	            <g:form action="salvar" >
	            	<g:hiddenField name="id" value="${parceiroInstance?.id}" />
                    <g:hiddenField name="version" value="${parceiroInstance?.version}" />
	            	<div class="divPadding">
		            	<g:each var="area" in="${areaList}">
		            		<g:areaEspecilidadeCheck area="${area}" especialidadeSelecionadas="${especialidadeList}" />
		            	</g:each>
	            	</div>
	            	<div class="divPadding">
		                <g:submitButton name="save" class="save" value="${message(code: 'default.button.save.label')}" />
		             </div>
	            </g:form>
            </div>


             <div class="divPadding">
               <a href="${request.contextPath}/principal/principal.gsp"><g:message code="voltar"/></a>
            </div>
        </div>
    </body>
</html>
