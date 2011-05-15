package com.methone.authentication
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils

/**
 *
 * Controller de logout gerado pelo plugin do spring security do grails
 *
 */
class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO  put any pre-logout code here
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
