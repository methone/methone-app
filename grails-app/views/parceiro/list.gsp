
<%@ page import="com.methone.Parceiro" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'parceiro.label', default: 'Parceiro')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'parceiro.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="login" title="${message(code: 'parceiro.login.label', default: 'Login')}" />
                        
                            <g:sortableColumn property="email" title="${message(code: 'parceiro.email.label', default: 'Email')}" />
                        
                            <g:sortableColumn property="senha" title="${message(code: 'parceiro.senha.label', default: 'Senha')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'parceiro.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="interesse" title="${message(code: 'parceiro.interesse.label', default: 'Interesse')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${parceiroInstanceList}" status="i" var="parceiroInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${parceiroInstance.id}">${fieldValue(bean: parceiroInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: parceiroInstance, field: "login")}</td>
                        
                            <td>${fieldValue(bean: parceiroInstance, field: "email")}</td>
                        
                            <td>${fieldValue(bean: parceiroInstance, field: "senha")}</td>
                        
                            <td>${fieldValue(bean: parceiroInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: parceiroInstance, field: "interesse")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${parceiroInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
