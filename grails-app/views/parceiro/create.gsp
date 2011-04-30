

<%@ page import="com.methone.Parceiro" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'parceiro.label', default: 'Parceiro')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
                                    <label for="login"><g:message code="parceiro.login.label" default="Login" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'login', 'errors')}">
                                    <g:textField name="login" value="${parceiroInstance?.login}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="email"><g:message code="parceiro.email.label" default="Email" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'email', 'errors')}">
                                    <g:textField name="email" value="${parceiroInstance?.email}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="senha"><g:message code="parceiro.senha.label" default="Senha" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'senha', 'errors')}">
                                    <g:passwordField name="senha" value="${parceiroInstance?.senha}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="nome"><g:message code="parceiro.nome.label" default="Nome" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'nome', 'errors')}">
                                    <g:textField name="nome" value="${parceiroInstance?.nome}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="interesse"><g:message code="parceiro.interesse.label" default="Interesse" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'interesse', 'errors')}">
                                    <g:select name="interesse" from="${com.methone.enumeration.Interesse?.values()}" keys="${com.methone.enumeration.Interesse?.values()*.name()}" value="${parceiroInstance?.interesse?.name()}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="ranking"><g:message code="parceiro.ranking.label" default="Ranking" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'ranking', 'errors')}">
                                    <g:select name="ranking.id" from="${com.methone.Ranking.list()}" optionKey="id" value="${parceiroInstance?.ranking?.id}" noSelection="['null': '']" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cep"><g:message code="parceiro.cep.label" default="Cep" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'cep', 'errors')}">
                                    <g:textField name="cep" value="${parceiroInstance?.cep}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="cidade"><g:message code="parceiro.cidade.label" default="Cidade" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'cidade', 'errors')}">
                                    <g:textField name="cidade" value="${parceiroInstance?.cidade}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="endereco"><g:message code="parceiro.endereco.label" default="Endereco" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'endereco', 'errors')}">
                                    <g:textField name="endereco" value="${parceiroInstance?.endereco}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="estado"><g:message code="parceiro.estado.label" default="Estado" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'estado', 'errors')}">
                                    <g:textField name="estado" value="${parceiroInstance?.estado}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="telefone"><g:message code="parceiro.telefone.label" default="Telefone" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: parceiroInstance, field: 'telefone', 'errors')}">
                                    <g:textField name="telefone" value="${parceiroInstance?.telefone}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
