package gr.hua.dit.feeding_service_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.user.User;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@GetMapping
	public String getAdminHomePage(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "admin-home";
	}
	
	@PostMapping("/create_user")
	public String createUser(@ModelAttribute("user") User user) {
		userService.createUser(user);
		return "redirect:/admin";
		
	}

}
