<g:if test="${message}">
    <div class="message">${message}</div>
</g:if>
<g:hasErrors bean="${parceiroInstance}">
    <div class="errors">
        <g:renderErrors bean="${parceiroInstance}" as="list" />
    </div>
</g:hasErrors>
<g:form action="${action}" enctype="${enctype}">
    <div class="dialog">
             <g:if test="${cadastro == 'false'}">
                 <g:hiddenField name="id" value="${parceiroInstance?.id}" />
                 <g:hiddenField name="version" value="${parceiroInstance?.version}" />

				 <g:if test="${parceiroInstance?.urlImagem != null}">
					 <div class="divPadding">
					   <img src="${resource(dir: diretorioImagem, file: parceiroInstance?.urlImagem)}"  style="width:140px;height:140px" />
				     </div>
	             </g:if>
	             <div class="divPadding">
	                <label style="width:auto"  for="file"><g:message code="selecioneImagem"  /></label>
	             </div>
	             <div class="clear"></div>
				 <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'urlImagem', 'error')}">
				     <input type="file" name="file" id="file"/>
				 </div>
				 <br /><br />
             </g:if>
             <g:if test="${cadastro == 'true'}">
                 <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'username', 'error')}">
                     <label for="username"><g:message code="login" /></label>
                     <g:textField name="username" value="${parceiroInstance?.username}"  maxlength="250"/>
                 </div>
                 <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'email', 'error')}">
                     <label for="email"><g:message code="email"  /></label>
                     <g:textField name="email" value="${parceiroInstance?.email}" maxlength="250" />
                 </div>
             	 <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'password', 'error')}">
             	 	 <label for="password"><g:message code="senha"  /></label>
             	 	 <g:passwordField name="password" value="${parceiroInstance?.password}" maxlength="250" />
             	 </div>
              </g:if>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'nome', 'error')}">
                   <label for="nome"><g:message code="nome" default="Nome" /></label>
                   <g:textField name="nome" value="${parceiroInstance?.nome}" maxlength="250" />
              </div>
              <g:if test="${cadastro == 'false'}">
              		<div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'empresa', 'error')}">
              		  <label for="empresa"><g:message code="empresa"  /></label>
              		  <g:textField name="empresa" value="${parceiroInstance?.empresa}" maxlength="250" />
              		</div>
              </g:if>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'interesse', 'error')}">
                  <label><g:message code="interesse" /></label>
                  <g:each in="${com.methone.enumeration.Interesse.values()}" var="interesseEnum">
                        <div class="left">
                          <g:radio class="checkInput" id="${interesseEnum.name()}" name="interesse" value="${interesseEnum}" checked="${parceiroInstance?.interesse.equals(interesseEnum)}" />
                          <label class="checkLabel" for="${interesseEnum.name()}">${message(code: interesseEnum.bundleKey)}</label>
                        </div>
                   </g:each>
              </div>
              <div class="clear"></div>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'cep', 'error')}">
                  <label for="cep"><g:message code="cep" /></label>
                  <g:textField name="cep" value="${parceiroInstance?.cep}" />
              </div>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'cidade', 'error')}">
                 <label for="cidade"><g:message code="cidade" /></label>
                 <g:textField name="cidade" value="${parceiroInstance?.cidade}" maxlength="250" />
              </div>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'endereco', 'error')}">
                 <label for="endereco"><g:message code="endereco"  /></label>
                 <g:textField name="endereco" value="${parceiroInstance?.endereco}" maxlength="250" />
              </div>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'estado', 'error')}">
                  <label for="estado"><g:message code="estado"  /></label>
                  <g:textField name="estado" value="${parceiroInstance?.estado}" maxlength="250" />
              </div>
              <div class="divPadding ${hasErrors(bean: parceiroInstance, field: 'telefone', 'error')}">
				 <label for="telefone"><g:message code="telefone"  /></label>
				 <g:textField name="telefone" value="${parceiroInstance?.telefone}" />
              </div>
              <div class="divPadding">
                <g:submitButton name="create" class="save" value="${message(code: 'default.button.save.label', default: 'Create')}" />
             </div>
     </div>

</g:form>
<script type="text/javascript">
            jQuery(function($){
            	   $("#telefone").mask("(99) 9999-9999");
            	   $("#cep").mask("99999-999");
            	});
</script>