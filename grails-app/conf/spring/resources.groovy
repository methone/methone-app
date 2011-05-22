import grails.util.GrailsUtil;

// Place your Spring DSL code here
beans = {
  //  no futuro pode-se escolher o que sera executado conforme o ambiente
  // GrailsUtil.environment == "development"

   initializeDatabase(database.InitializeDatabase) {
	 parceiroService = ref("parceiroService")
   }

}
