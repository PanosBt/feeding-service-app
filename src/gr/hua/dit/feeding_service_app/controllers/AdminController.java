package gr.hua.dit.feeding_service_app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.feeding_service_app.entities.Admin;
import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;
import gr.hua.dit.feeding_service_app.model_helper.NewUserHelper;
import gr.hua.dit.feeding_service_app.services.AdminService;
import gr.hua.dit.feeding_service_app.services.ClerkService;
import gr.hua.dit.feeding_service_app.services.StudentService;
import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.utilites.AuthorityUtilities;

@Controller
@RequestMapping("/admin")
@Secured(AuthorityUtilities.ADMIN_ROLE)
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private ClerkService clerkService;

	@Autowired
	private StudentService studentService;

	@GetMapping
	public String getAdminHomePage(Model model, @RequestParam Map<String, String> params) {
		NewUserHelper newUser = new NewUserHelper();
		// modelAttribute used for new user creation
		model.addAttribute("user", newUser);
		// modelAttribute used for user search
		model.addAttribute("username", new String());
		
		// add parameters to model after redirection
		String userCreated;
		if ((userCreated = params.get("userCreated")) != null)
			model.addAttribute("userCreated", Boolean.parseBoolean(userCreated));
		String modUserFound;
		if ((modUserFound = params.get("modUserFound")) != null)
			model.addAttribute("modUserFound", Boolean.parseBoolean(modUserFound));
		String delUserFound;
		if ((delUserFound = params.get("delUserFound")) != null)
			model.addAttribute("delUserFound", Boolean.parseBoolean(delUserFound));
		
		return "admin-home";
	}

	@PostMapping("/create_user")
	public String createUser(@Valid @ModelAttribute("user") NewUserHelper newUser, BindingResult result, Model model) {
		if (result.hasErrors())
			return "admin-home";

		return "redirect:/admin?userCreated=" + userService.createUser(newUser);

	}

	@RequestMapping("/modify_user_search")
	public String searchUser(@RequestParam Map<String, String> params, Model model) {
		
		//Redirection string if user is not found
		String userNotFoundRed = "redirect:/admin?modUserFound=false";
		
		String username;

		if (params.containsKey("username"))
			username = params.get("username");
		else 
			return userNotFoundRed;
		
		if (params.containsKey("userUpdated")) 
			model.addAttribute("userUpdated", Boolean.parseBoolean(params.get("userUpdated")));
				
		// check if user exists
		if (userService.searchUser(username) == null)
			return userNotFoundRed;

		// get user role
		String role = userService.getUserHigherRole(username);

		// depending on user role, get user from the corresponding service
		// and add the user to the model
		switch (role) {
		case AuthorityUtilities.ADMIN_ROLE:
			Admin admin;
			if ((admin = adminService.searchForAdmin(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", admin);
			break;
		case AuthorityUtilities.CLERK_ROLE:
		case AuthorityUtilities.SUPERVISOR_ROLE:
			Clerk clerk;
			if ((clerk = clerkService.searchForClerk(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", clerk);
			break;
		case AuthorityUtilities.STUDENT_ROLE:
			Student student;
			if ((student = studentService.searchForStudent(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", student);
			break;
		default:
			return userNotFoundRed;
		}

		// if everything went well
		// add user role to the model and return
		model.addAttribute("role", normalizeRole(role));
		model.addAttribute("user_modified", new ModUserHelper(username));
		
		return "modify-user";
	}

	// Normalize role names for prettier output
	private String normalizeRole(String role) {
		return AuthorityUtilities.NORMALIZED_ROLES.containsKey(role) 
				? AuthorityUtilities.NORMALIZED_ROLES.get(role)
				: "";
	}
	
	@PostMapping("/modify_user/{username}")
	public String modifyUser(@ModelAttribute("user_modified") ModUserHelper modUser, 
			@PathVariable("username") String username) {
		
		// redirection string when finished
		String redStr = "redirect:/admin/modify_user_search?"
						+ "username=" + username
						+ "&userUpdated=";
	
		modUser.setUsername(username);
		userService.updateUser(modUser);
		return redStr + "true";
	}

	@PostMapping("/delete_user")
	public String deleteUser(@RequestParam("username") String username) {
		return "redirect:/admin?delUserFound=" + userService.deleteUser(username);

	}
//	
//	// invert the result of normalizeRole()
//	private String deNormalizeRole(String role) {
//		String deNormRole;
//		try {
//			deNormRole = AuthorityUtilities.NORMALIΖED_ROLES.entrySet()
//					.stream()
//					.filter(entry -> Objects.equals(entry.getValue(), role))
//					.findFirst()
//					.get()
//					.getKey();
//		}
//		catch(NullPointerException | NoSuchElementException | IllegalStateException ex) {
//			ex.printStackTrace();
//			deNormRole = "";
//		}
//		return deNormRole;
//
//	}

}
