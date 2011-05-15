

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
        <div class="nav">
            <span class="menuButton">
               <a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
            </span>
        </div>
        <div class="body">
            <h1><g:message code="cadastroParceiro" /></h1>
            <g:if test="${flash.message}">
               <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${parceiroInstance}">
            <div class="errors">
                <g:renderErrors bean="${parceiroInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="username"><g:message code="login" default="Login" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'username', 'errors')}">
                                    <g:textField name="username" value="${parceiroInstance?.username}"  maxlength="250"/>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="email" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${parceiroInstance?.email}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for=password><g:message code="senha" default="Senha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'password', 'errors')}">
                                    <g:passwordField name="password" value="${parceiroInstance?.password}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="nome" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${parceiroInstance?.nome}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="interesse"><g:message code="interesse" default="Interesse" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'interesse', 'errors')}">
                                    <g:each in="${com.methone.enumeration.Interesse.values()}" var="interesseEnum">
                                       <g:radio id="${interesseEnum.name()}" name="interesse" value="${interesseEnum}" checked="${parceiroInstance?.interesse.equals(interesseEnum)}" />
                                       <label for="${interesseEnum.name()}">${message(code: interesseEnum.bundleKey)}</label>
                                    </g:each>
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cep"><g:message code="cep" default="Cep" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'cep', 'errors')}">
                                    <g:textField name="cep" value="${parceiroInstance?.cep}" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cidade"><g:message code="cidade" default="Cidade" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'cidade', 'errors')}">
                                    <g:textField name="cidade" value="${parceiroInstance?.cidade}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endereco"><g:message code="endereco" default="Endereco" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'endereco', 'errors')}">
                                    <g:textField name="endereco" value="${parceiroInstance?.endereco}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="estado"><g:message code="estado" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'estado', 'errors')}">
                                    <g:textField name="estado" value="${parceiroInstance?.estado}" maxlength="250" />
                                </td>
                            </tr>

                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefone"><g:message code="telefone" default="Telefone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'telefone', 'errors')}">
                                    <g:textField name="telefone" value="${parceiroInstance?.telefone}" />
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
            <script type="text/javascript">
            jQuery(function($){
            	   $("#telefone").mask("(99) 9999-9999");
            	   $("#cep").mask("99999-999");
            	});
            </script>
        </div>
    </body>
</html>
