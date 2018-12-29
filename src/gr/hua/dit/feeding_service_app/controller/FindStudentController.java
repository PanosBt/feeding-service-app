package gr.hua.dit.feeding_service_app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import gr.hua.dit.feeding_service_app.Objects.StudentObject;

@Controller
public class FindStudentController {
	@GetMapping("/find-student")
	public String CardCreated(Model model) {
//		String studentAM = null;
//		model.addAttribute("find-student", studentAM);
		return "find-student";
	
	}
	
	@PostMapping("/student-found")
	public String CardCreation(@ModelAttribute String id,ModelMap model) {
//		model.addAttribute("studentAM",id);
		return "found-student";
	}

}
