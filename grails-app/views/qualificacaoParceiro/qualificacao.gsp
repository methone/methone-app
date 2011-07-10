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
            <div class="dialog">
            	<g:each var="area" in="${areaList}">
            		<g:areaEspecilidadeCheck area="${area}" />
            	</g:each>
            </div>

             <div class="divPadding">
               <a href="${request.contextPath}/principal/principal.gsp"><g:message code="voltar"/></a>
            </div>
        </div>
    </body>
</html>
