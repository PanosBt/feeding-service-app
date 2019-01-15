package gr.hua.dit.feeding_service_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gr.hua.dit.feeding_service_app.Objects.StudentLimit;
@Controller
public class StudentMarkingController {

	@GetMapping("/student-marking")
	public String CardCreated(Model model) {
	String marking = "50";
	model.addAttribute("marking", marking);
	return "show-student-marking";
		
	}
}

