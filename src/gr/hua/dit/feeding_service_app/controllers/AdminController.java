package gr.hua.dit.feeding_service_app.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.Principal;
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
import gr.hua.dit.feeding_service_app.utilites.CustomAuthorityUtilities;

@Controller
@RequestMapping("/admin")
@Secured(CustomAuthorityUtilities.ADMIN_ROLE)
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
	public String getAdminHomePage(Model model, @RequestParam Map<String, String> params, Principal principal) {
		
		Admin admin = adminService.getAdmin(principal.getName());
		
		model.addAttribute("curUserUsername", principal.getName());
		model.addAttribute("userFirstName", admin.getFirstName());
		model.addAttribute("userLastName", admin.getLastName());
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
		String userExists;
		if ((userExists = params.get("userExists")) != null)
			model.addAttribute("userExists", Boolean.parseBoolean(userExists));
		
		
		return "admin-home";
	}

	@PostMapping("/create_user")
	public String createUser(@Valid @ModelAttribute("user") NewUserHelper newUser, BindingResult result, Model model) {
		if (result.hasErrors())
			return "admin-home";
		
		if (userService.searchUser(newUser.getUsername()) != null)
			return "redirect:/admin?userExists=" + true;

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
		case CustomAuthorityUtilities.ADMIN_ROLE:
			Admin admin;
			if ((admin = adminService.getAdmin(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", admin);
			break;
		case CustomAuthorityUtilities.CLERK_ROLE:
		case CustomAuthorityUtilities.SUPERVISOR_ROLE:
			Clerk clerk;
			if ((clerk = clerkService.getClerk(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", clerk);
			break;
		case CustomAuthorityUtilities.STUDENT_ROLE:
			Student student;
			if ((student = studentService.getStudent(username)) == null)
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
		return CustomAuthorityUtilities.NORMALIZED_ROLES.containsKey(role) 
				? CustomAuthorityUtilities.NORMALIZED_ROLES.get(role)
				: "";
	}
	
	@PostMapping("/modify_user/{username}")
	public String modifyUser(@ModelAttribute("user_modified") ModUserHelper modUser, 
			@PathVariable("username") String username) {
		
		String mod_username = username;
		String userUpdated = "true";
		
		try {
			mod_username = URLEncoder.encode(mod_username, "UTF-8"); //encode username to UTF-8 so usernames with greek characters are supported in redirect link
		} catch (UnsupportedEncodingException e) {
			userUpdated = "false";
			e.printStackTrace();
		}
		
		// redirection string when finished
		String redStr = "redirect:/admin/modify_user_search?"
						+ "username=" + mod_username
						+ "&userUpdated=";
	
		modUser.setUsername(username);
		modUser.setIdentityCardNO(modUser.getIdentityCardNO().replaceAll("\\s+", ""));
		boolean dataInit = studentService.getStudent(username) != null?
				studentService.getStudent(username).isData_init()
				:false;
		modUser.setData_init(dataInit);
		userService.updateUser(modUser);
		return redStr + userUpdated;
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
