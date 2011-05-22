import database.InitializeDatabase

class BootStrap {
	def initializeDatabase

    def init = { servletContext ->
		initializeDatabase.init();
    }


    def destroy = {

	}
}
