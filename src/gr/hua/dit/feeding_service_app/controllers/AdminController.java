package gr.hua.dit.feeding_service_app.controllers;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

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
		NewUserHelper user = new NewUserHelper();
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
	public String createUser(@Valid @ModelAttribute("user") NewUserHelper newUser, BindingResult result, Model model) {
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
		return AuthorityUtilities.NORMALIΖED_ROLES.containsKey(role) 
				? AuthorityUtilities.NORMALIΖED_ROLES.get(role)
				: "";
	}
	
	// invert the result of normalizeRole()
	private String deNormalizeRole(String role) {
		String deNormRole;
		try {
			deNormRole = AuthorityUtilities.NORMALIΖED_ROLES.entrySet()
					.stream()
					.filter(entry -> Objects.equals(entry.getValue(), role))
					.findFirst()
					.get()
					.getKey();
		}
		catch(NullPointerException | NoSuchElementException | IllegalStateException ex) {
			ex.printStackTrace();
			deNormRole = "";
		}
		return deNormRole;

	}

	@PostMapping("/modify_user")
	public String modifyUser(@ModelAttribute("user_modified") ModUserHelper modUser, @ModelAttribute("role") String role) {
		String redStr = "redirect:/admin/modify_user_search?userUpdated=";
		
		if ((userService.searchUser(modUser.getUsername()) == null))
			return redStr + "false";
		
		userService.updateUser(modUser, deNormalizeRole(role));
		return "unimplemented";
	}

	@PostMapping("/delete_user")
	public String deleteUser(@RequestParam("username") String username) {
		return "redirect:/admin?delUserFound=" + userService.deleteUser(username);

	}

}
