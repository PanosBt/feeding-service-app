package gr.hua.dit.feeding_service_app.controllers;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.Objects.StudentLimit;
import gr.hua.dit.feeding_service_app.Objects.StudentObject;
import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.dao.StudentDAOImpl;
import gr.hua.dit.feeding_service_app.entities.Student;

@Controller
@RequestMapping(value = "/clerk")
public class ClerkController {
	
	@Autowired
	private StudentDAO studentDAO;
	
	/**
	 * @return card-creation-form
	 */
	
	@GetMapping("/card-creation")
	public String CardCreation(Model model) {
		Student student = new Student();
		model.addAttribute("card-creation-form", student);
		return "card-creation-form";
	
	}
	
	/**
	 * @return card-created
	 */
	
	@PostMapping("/card-created")
	public String CardCreated(@ModelAttribute Student student,ModelMap model) {
		return "card-created";
	}
	
	
	/**
	 * @return change-student-limit
	 */
	
	@GetMapping("/change-student-limit")
	public String ChangeLimit(Model model) {
		model.addAttribute("change-student-limit", new StudentLimit());
		return "change-student-limit";
	
	}
	
	/**
	 * @return changed-student-limit
	 */
	
	@PostMapping("/changed-student-limit")
	public String ChangedLimit(@ModelAttribute StudentLimit limit,ModelMap model) {
		model.addAttribute("newLimit",limit.getNewLimit());
		return "changed-student-limit";
	}
	
	/**
	 * @return find-student
	 */
	
	@GetMapping("/find-student")
	public String FindStudent(Model model) {
//		String studentAM = null;
//		model.addAttribute("find-student", studentAM);
		return "find-student";
	
	}
	
	/**
	 * @return found-student
	 */
	
	@PostMapping("/found-student")
	public String FoundStudent(@ModelAttribute String id,ModelMap model) {
//		model.addAttribute("studentAM",id);
		return "found-student";
	}
	
	/**
	 * @return show-student-marking
	 */
	
	@GetMapping("/student-marking")
	public String CardCreated(Model model) {
	String marking = "50";
	model.addAttribute("marking", marking);
	return "show-student-marking";
		
	}
	
}
