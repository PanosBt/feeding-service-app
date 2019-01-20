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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.feeding_service_app.entities.Admin;
import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.entities.User;
import gr.hua.dit.feeding_service_app.services.AdminService;
import gr.hua.dit.feeding_service_app.services.ClerkService;
import gr.hua.dit.feeding_service_app.services.StudentService;
import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.utilites.AuthorityUtilities;
import gr.hua.dit.feeding_service_app.utilites.NewUserUtil;

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
		NewUserUtil user = new NewUserUtil();
		model.addAttribute("user", user);
		model.addAttribute("username", new String());
		
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
	public String createUser(@Valid @ModelAttribute("user") NewUserUtil newUser, BindingResult result, Model model) {
		if (result.hasErrors())
			return "admin-home";

		return "redirect:/admin?userCreated=" + userService.createUser(newUser);

	}

	@PostMapping("/modify_user_search")
	public String searchUser(@RequestParam("username") String username, Model model) {
		String userNotFoundRed = "redirect:/admin?modUserFound=false";

		// find user
		User user = userService.searchUser(username);
		if (user == null)
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
			model.addAttribute("user_modified", admin);
			break;
		case AuthorityUtilities.CLERK_ROLE:
		case AuthorityUtilities.SUPERVISOR_ROLE:
			Clerk clerk;
			if ((clerk = clerkService.searchForClerk(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", clerk);
			model.addAttribute("user_modified", clerk);
			break;
		case AuthorityUtilities.STUDENT_ROLE:
			Student student;
			if ((student = studentService.searchForStudent(username)) == null)
				return userNotFoundRed;
			model.addAttribute("user", student);
			model.addAttribute("user_modified", student);
			break;
		default:
			return userNotFoundRed;
		}

		// if everything went well
		// add user role to the model and return
		model.addAttribute("role", normaliseRole(role));
		return "modify-user";
	}

	private String normaliseRole(String role) {
		switch (role) {
		case AuthorityUtilities.ADMIN_ROLE:
			return "Administrator";
		case AuthorityUtilities.CLERK_ROLE:
			return "Clerk";
		case AuthorityUtilities.STUDENT_ROLE:
			return "Student";
		case AuthorityUtilities.SUPERVISOR_ROLE:
			return "Supervisor";
		default:
			return "";
		}
	}

	@PostMapping("/modify_user")
	public String modifyUser(@ModelAttribute("user_modified") Object user, @ModelAttribute("role") String role) {
		// TODO implement dah!
		return "unimplemented";
	}

	@PostMapping("/delete_user")
	public String deleteUser(@RequestParam("username") String username) {
		return "redirect:/admin?delUserFound=" + userService.deleteUser(username);

	}

}
