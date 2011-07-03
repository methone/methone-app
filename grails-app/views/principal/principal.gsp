<html>
    <head>
        <meta name='layout' content='main' />
        <title>Welcome to Grails</title>
    </head>
    <body>
        <div>
            <div class="dialog">
                <h2>Available Controllers:</h2>
                <ul>
                   <li>
                         <a href="${request.contextPath}/area">com.methone.AreaController</a>
                   </li>
                   <li>
                      <a href="${request.contextPath}/especialidade">com.methone.EspecialidadeController</a>
                   </li>
                   <li>
                      <a href="${request.contextPath}/detalheParceiro/detail"><g:message code="detalheParceiro"  /></a>
                   </li>
                   <li>
                      <a href="${request.contextPath}/trocaSenha"><g:message code="trocaSenhaEmail"  /></a>
                   </li>
                </ul>                
            </div>
        </div>
    </body>
</html>
