<html>
    <head>
        <meta name='layout' content='main' />
        <title>Welcome to Grails</title>
    </head>
    <body>

        <div id="pageBody">

            <div id="controllerList" class="dialog">
                <h2>Available Controllers:</h2>
                <ul>
                   <li class="controller">
                         <a href="${request.contextPath}/area">com.methone.AreaController</a>
                   </li>
                   <li class="controller">
                      <a href="${request.contextPath}/especialidade">com.methone.EspecialidadeController</a>
                   </li>
                   <li class="controller">
                      <a href="${request.contextPath}/detalheParceiro/detail"><g:message code="detalheParceiro"  /></a>
                   </li>
                   <li class="controller">
                      <a href="${request.contextPath}/trocaSenha"><g:message code="trocaSenhaEmail"  /></a>
                   </li>

                </ul>
            </div>
        </div>
    </body>
</html>
