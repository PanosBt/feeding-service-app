package gr.hua.dit.feeding_service_app.api.authorize;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.feeding_service_app.api.response_helper.AuthorizeResponse;
import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.utilites.AuthorityUtilities;

@RestController
@RequestMapping("/api/student/authorize")
public class AuthorizeRestController {
	
	@Autowired
	private UserService userService;

	@GetMapping
	public AuthorizeResponse authorize(@RequestParam Map<String, String> params) {
		String username, password;
		
		if(params.containsKey("username"))
			username = params.get("username");
		else
			//TODO Throw Exception!
			return null;
		if(params.containsKey("password"))
			password = params.get("password");
		else
			//TODO Throw Exception!
			return null;
			
		boolean authenticated = userService.authenticateUser(username, password);
		
		boolean isStudent = false;
		
		// check if user is student, only if he/she is authenticated
		if (authenticated)
			isStudent = userService.getUserHigherRole(username).equals(AuthorityUtilities.STUDENT_ROLE);
		
		return new AuthorizeResponse(username, authenticated, isStudent);
		
	}
}
