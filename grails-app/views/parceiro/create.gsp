<%@ page import="com.methone.Parceiro" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>
          <g:message code="cadastroParceiro"  />
        </title>
    </head>
    <body>
        <div class="body">
            <h1><g:message code="cadastroParceiro" /></h1>
            <g:formularioParceiro action="save" parceiroInstance="${parceiroInstance}" cadastro="true" message="${flash.message}" />
            <div>
               <a href="${request.contextPath}/login/auth"><g:message code="voltar"/></a>
            </div>
        </div>
    </body>
</html>
