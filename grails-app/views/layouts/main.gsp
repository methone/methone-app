<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Freelancer" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'style.css')}" />
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <!--
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        -->
        <g:layoutHead />
        <g:javascript library="application" />
        <g:javascript library="jquery" plugin="jquery"/>
        <g:javascript src="jquery.maskedinput-1.3.min.js" />

    </head>
    <body>
        <div id="header">
           <div class="tail"></div>
	       <div class="wrapper">
	          <div id="logo">
	             <img alt="" title="" src="${resource(dir:'images/skin',file:'logo.png')}"">
	          </div>
	        </div>
        </div>
        <sec:ifLoggedIn>
            <sec:username /> <g:link controller="logout"> <g:message code="sair" /></g:link>
        </sec:ifLoggedIn>
        <g:layoutBody />
    </body>
</html>