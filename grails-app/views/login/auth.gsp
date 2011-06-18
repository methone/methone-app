<head>
   <meta name='layout' content='main' />
   <title><g:message code="login" /></title>
</head>

<body>
	<div id='login'>
		<div>
			<g:if test='${flash.message}'>
			<div>${flash.message}</div>
			</g:if>
			<div><g:message code="facaLogin" /> </div>
			<form action='${postUrl}' method='POST' id='loginForm'  autocomplete='off'>
				<div class="divInternaFormulario">
					<label for='username'><g:message code="login" /></label>
					<input type='text' class="inputText"  name='j_username' id='username' maxlength="250" />
				</div>
				<div class="divInternaFormulario">
					<label for='password'><g:message code="senha" /></label>
					<input type='password' class="inputText"  name='j_password' id='password' maxlength="250" />
				</div>
				<div class="divInternaFormulario">
					<label for='remember_me'><g:message code="lembrarMe" /></label>
					<input type='checkbox' name='${rememberMeParameter}' id='remember_me'
					<g:if test='${hasCookie}'>checked='checked'</g:if> />
				</div>
				<div class="divInternaFormulario">
					<input type='submit' value='${message(code: 'entrar')}' />
				</div>
			</form>
		</div>
		<div>
		  <a href="${request.contextPath}/parceiro/create"><g:message code="cadastroParceiro"  /></a>
		</div>
	</div>
<script type='text/javascript'>
<!--
(function(){
	document.forms['loginForm'].elements['j_username'].focus();
})();
// -->
</script>
</body>
