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
            <h1><g:message code="editeDetalhesPerfil" /></h1>
            <g:formularioParceiro action="update" parceiroInstance="${parceiroInstance}"
              cadastro="false" message="${flash.message}" enctype="multipart/form-data" />
             <div class="divPadding">
               <a href="${request.contextPath}/principal/principal.gsp"><g:message code="voltar"/></a>
            </div>
        </div>
    </body>
</html>
