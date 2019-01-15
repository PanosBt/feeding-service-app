package gr.hua.dit.feeding_service_app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import gr.hua.dit.feeding_service_app.Objects.StudentObject;

@Controller
public class CardCreationController {
	@GetMapping("/card-creation")
	public String CardCreated(Model model) {
		model.addAttribute("card-creation-form", new StudentObject());
		return "card-creation-form";
	
	}
	
	@PostMapping("/card-creation-complete")
	public String CardCreation(@ModelAttribute StudentObject student,ModelMap model) {
		model.addAttribute("firstName",student.getFirstName());
		return "card-created";
	}
	

}
