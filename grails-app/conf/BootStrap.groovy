import database.InitialzeDatabase

class BootStrap {
	def initialzeDatabase

    def init = { servletContext ->
		initialzeDatabase.init();
    }


    def destroy = {

	}
}
