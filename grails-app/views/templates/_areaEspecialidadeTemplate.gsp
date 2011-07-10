<div>
     <span id="open_${area.id}" class="hand active" >+ ${area}</span>
     <span id="close_${area.id}" class="hand inactive" >- ${area}</span>

     <div id="divFade_${area.id}" class="inactive" style="width:600px">
		 <g:each var="especialidade" in="${especialidades}">
					<div class="divPadding left">
						<input class="checkInput" type="checkbox" name="especialidade" id="especialidade_${especialidade.id}"
					   		<g:each var="selecionada" in="${especialidadeSelecionadas}">
		                  		<g:if test="${selecionada.id == especialidade.id}">checked="checked"</g:if>
					   		</g:each>
						/>
						<label for="especialidade_${especialidade.id}" class="checkLabel" style="width: 170px;">${especialidade}</label>
					</div>
	 	 </g:each>
 	 </div>
 	 <div class="clear"></div>
 	 <br />
</div>

<script type="text/javascript">
    $("#open_${area.id}").click(function () {
      $("#divFade_${area.id}").fadeIn('fast', function() {
    	  $("#open_${area.id}").removeClass("active").addClass("inactive");
    	  $("#close_${area.id}").removeClass("inactive").addClass("active");
      });
    });
    $("#close_${area.id}").click(function () {
        $("#divFade_${area.id}").fadeOut('fast', function() {
          $("#close_${area.id}").removeClass("active").addClass("inactive");
       	  $("#open_${area.id}").removeClass("inactive").addClass("active");
        });
      });
</script>




