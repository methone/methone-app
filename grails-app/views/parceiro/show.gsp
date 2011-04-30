
<%@ page import="com.methone.Parceiro" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'parceiro.label', default: 'Parceiro')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="dialog">
                <table>
                    <tbody>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.id.label" default="Id" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "id")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.login.label" default="Login" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "login")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.email.label" default="Email" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "email")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.senha.label" default="Senha" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "senha")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.nome.label" default="Nome" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "nome")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.interesse.label" default="Interesse" /></td>
                            
                            <td valign="top" class="value">${parceiroInstance?.interesse?.encodeAsHTML()}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.ranking.label" default="Ranking" /></td>
                            
                            <td valign="top" class="value"><g:link controller="ranking" action="show" id="${parceiroInstance?.ranking?.id}">${parceiroInstance?.ranking?.encodeAsHTML()}</g:link></td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.atividadesProjeto.label" default="Atividades Projeto" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${parceiroInstance.atividadesProjeto}" var="a">
                                    <li><g:link controller="atividadeProjeto" action="show" id="${a.id}">${a?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.cep.label" default="Cep" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "cep")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.cidade.label" default="Cidade" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "cidade")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.endereco.label" default="Endereco" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "endereco")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.estado.label" default="Estado" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "estado")}</td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.pagamentos.label" default="Pagamentos" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${parceiroInstance.pagamentos}" var="p">
                                    <li><g:link controller="pagamento" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.projetos.label" default="Projetos" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${parceiroInstance.projetos}" var="p">
                                    <li><g:link controller="projeto" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.propostas.label" default="Propostas" /></td>
                            
                            <td valign="top" style="text-align: left;" class="value">
                                <ul>
                                <g:each in="${parceiroInstance.propostas}" var="p">
                                    <li><g:link controller="proposta" action="show" id="${p.id}">${p?.encodeAsHTML()}</g:link></li>
                                </g:each>
                                </ul>
                            </td>
                            
                        </tr>
                    
                        <tr class="prop">
                            <td valign="top" class="name"><g:message code="parceiro.telefone.label" default="Telefone" /></td>
                            
                            <td valign="top" class="value">${fieldValue(bean: parceiroInstance, field: "telefone")}</td>
                            
                        </tr>
                    
                    </tbody>
                </table>
            </div>
            <div class="buttons">
                <g:form>
                    <g:hiddenField name="id" value="${parceiroInstance?.id}" />
                    <span class="button"><g:actionSubmit class="edit" action="edit" value="${message(code: 'default.button.edit.label', default: 'Edit')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </g:form>
            </div>
        </div>
    </body>
</html>
