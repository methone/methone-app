<style>
    .hand {cursor:pointer; }
    .active {display:block;}
    .inactive {display:none;}
</style>
 
 
 <div>
     <span id="open" class="hand active" >+ ${area}</span><span id="close" class="hand inactive" >- ${area}</span>
     <div id="divFade" class="inactive" >
	 <g:each var="especialidade" in="${especialidades}">
			<input type="checkbox" name="especialidade"/> ${especialidade}<br />			
 	 </g:each>
 	 </div>    
     
</div>

<script>
    $("#open").click(function () {
      $("#divFade").fadeIn('fast', function() {
          // Animation complete
    	  $("#open").removeClass("active").addClass("inactive");
    	  $("#close").removeClass("inactive").addClass("active");
      });
    });
    $("#close").click(function () {
        $("#divFade").fadeOut('fast', function() {
            // Animation complete
          $("#close").removeClass("active").addClass("inactive");
       	  $("#open").removeClass("inactive").addClass("active");
        });
      });
</script>


 

