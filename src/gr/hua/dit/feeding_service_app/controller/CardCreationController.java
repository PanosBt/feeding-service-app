package gr.hua.dit.feeding_service_app.controller;

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
	@GetMapping("/CardCreation")
	public String CardCreated(Model model) {
		model.addAttribute("CardCreationForm", new StudentObject());
		return "CardCreationForm";
	
	}
	
	@PostMapping("/CardCreationComplete")
	public String CardCreation(@ModelAttribute StudentObject student,ModelMap model) {
		model.addAttribute("firstName",student.getFirstName());
		return "CardCreated";
	}
	

}
