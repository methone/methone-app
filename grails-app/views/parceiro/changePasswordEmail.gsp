<html>
	<head>
    	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title>
        	<g:message code="trocaSenhaEmail" />
        </title>
    </head>
    <body>
		<div class="body">
        	<h1><g:message code="trocaSenhaEmail" /></h1>
			<g:if test="${message}">
				<div class="message">${message}</div>
			</g:if>
			<g:hasErrors bean="${parceiroInstance}">
			    <div class="errors">
			    	<g:renderErrors bean="${parceiroInstance}" as="list" />
			    </div>
			</g:hasErrors>
			<g:form action="savePasswordEmail" >
 				<div class="dialog">
			    	<table>
			        <tbody>
						<g:hiddenField name="id" value="${parceiroInstance?.id}" />
						<g:hiddenField name="version" value="${parceiroInstance?.version}" />
			            <tr class="prop">
			            	<td valign="top" class="name">
			                	<label for="email"><g:message code="email" /></label>
			                </td>
			                <td valign="top" class="value ">
			                    <g:textField name="email" value="${parceiroInstance?.email}" maxlength="250" />
			                </td>
	             		</tr>
			            <tr class="prop">
			            	<td valign="top" class="name">
			                	<label for="senhaAtual"><g:message code="senhaAtual" /></label>
			                </td>
			                <td valign="top" class="value ">
			                    <g:passwordField name="senhaAtual" value="" maxlength="250" />
			                </td>
	             		</tr>
			            <tr class="prop">
			            	<td valign="top" class="name">
			                	<label for="novaSenha"><g:message code="novaSenha" /></label>
			                </td>
			                <td valign="top" class="value ">
			                    <g:passwordField name="novaSenha" value="" maxlength="250" />
			                </td>
	             		</tr>
			            <tr class="prop">
			            	<td valign="top" class="name">
			                	<label for="confirmaNovaSenha"><g:message code="confirmaNovaSenha" /></label>
			                </td>
			                <td valign="top" class="value">
			                    <g:passwordField name="confirmaNovaSenha" value="" maxlength="250" />
			                </td>
	             		</tr>
					</tbody>
     			</table>
 			</div>
 			<div class="buttons">
     			<span class="button">
        			<g:submitButton name="create" class="save" value="${message(code: 'default.button.save.label', default: 'Create')}" />
     			</span>
 			</div>
		</g:form>
            <div>
               <a href="${request.contextPath}/principal/principal.gsp"><g:message code="voltar"/></a>
            </div>
        </div>
    </body>
</html>