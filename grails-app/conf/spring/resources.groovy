import com.methone.ParceiroService;

import grails.util.GrailsUtil;

// Place your Spring DSL code here
beans = {
  //  no futuro pode-se escolher o que sera executado conforme o ambiente
  // GrailsUtil.environment == "development"

   initializeDatabase(database.InitializeDatabase) {
	 parceiroService = ref("parceiroService")
   }

   if(GrailsUtil.environment == "development" || GrailsUtil.environment == "test"){
	   parceiroService(com.methone.ParceiroService) {
		   springSecurityService = ref("springSecurityService")
		   imageService =  ref("imageService")
		   diretorioImagem = "web-app/images/uploads/"
		   diretorioImagemRelativo = "images/uploads/"
	   }
   }
}
