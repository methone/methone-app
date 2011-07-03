<head>
   <meta name='layout' content='main' />
   <title><g:message code="login" /></title>
</head>

<body>
	
	<div id='login'>
	     <g:if test='${flash.message}'>
			  <div class="message">${flash.message}</div>
			</g:if>
		<div id="innerLogin">			
			<div class="divPadding divInfo"><g:message code="facaLogin" /> </div>
			<form action='${postUrl}' method='POST' id='loginForm'  autocomplete='off'>
				<div class="divPadding">
					<label for='username'><g:message code="login" /></label>
					<input type='text' name='j_username' id='username' maxlength="250" />
				</div>
				<div class="divPadding">
					<label for='password'><g:message code="senha" /></label>
					<input type='password' name='j_password' id='password' maxlength="250" />
				</div>
				<div class="divPadding">
					<label for='remember_me'><g:message code="lembrarMe" /></label>
					<input type='checkbox' name='${rememberMeParameter}' id='remember_me'
					<g:if test='${hasCookie}'>checked='checked'</g:if> />
				</div>
				<div class="divPadding">
					<input type='submit' value='${message(code: 'entrar')}' class="inputButtonRight" />
				</div>
				<div class="clear"></div>
			</form>
		</div>
		<div class="divPadding">
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
