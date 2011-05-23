
<%@ page import="com.methone.Especialidade" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'especialidade.label', default: 'Especialidade')}" />
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
                        
                            <g:sortableColumn property="id" title="${message(code: 'especialidade.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="nome" title="${message(code: 'especialidade.nome.label', default: 'Nome')}" />
                        
                            <g:sortableColumn property="descricao" title="${message(code: 'especialidade.descricao.label', default: 'Descricao')}" />
                        
                            <th><g:message code="especialidade.area.label" default="Area" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${especialidadeInstanceList}" status="i" var="especialidadeInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${especialidadeInstance.id}">${fieldValue(bean: especialidadeInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: especialidadeInstance, field: "nome")}</td>
                        
                            <td>${fieldValue(bean: especialidadeInstance, field: "descricao")}</td>
                        
                            <td>${fieldValue(bean: especialidadeInstance, field: "area")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${especialidadeInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
