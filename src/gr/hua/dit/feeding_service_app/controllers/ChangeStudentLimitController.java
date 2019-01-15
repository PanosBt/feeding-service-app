package gr.hua.dit.feeding_service_app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gr.hua.dit.feeding_service_app.Objects.StudentLimit;
import gr.hua.dit.feeding_service_app.Objects.StudentObject;

@Controller
public class ChangeStudentLimitController {
	@GetMapping("/change-student-limit")
	public String CardCreated(Model model) {
		model.addAttribute("change-student-limit", new StudentLimit());
		return "change-student-limit";
	
	}
	
	@PostMapping("/change-student-limit-complete")
	public String CardCreation(@ModelAttribute StudentLimit limit,ModelMap model) {
		model.addAttribute("newLimit",limit.getNewLimit());
		return "changed-student-limit";
	}
}
