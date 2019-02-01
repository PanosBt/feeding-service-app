package gr.hua.dit.feeding_service_app.controllers;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;
import gr.hua.dit.feeding_service_app.services.StudentService;
import gr.hua.dit.feeding_service_app.services.UserService;
import gr.hua.dit.feeding_service_app.utilites.AuthorityUtilities;
import gr.hua.dit.feeding_service_app.utilites.Utilities;


@Controller
@RequestMapping("/clerk")
@Secured(AuthorityUtilities.CLERK_ROLE)
public class ClerkController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudentService studentService;
	
	@RequestMapping
	public String ClerkHomePage(Model model, @RequestParam Map<String, String> params) {
		
		//checks if the limit was updated when the updateStudentLimit was called
		if (params.containsKey("studentLimitUpdated")) 
			model.addAttribute("studentLimitUpdated", Boolean.parseBoolean(params.get("studentLimitUpdated")));
		//model attribute to show current student limit
		model.addAttribute("limit", Utilities.getStudentLimit());
	
		return "clerk-home";
	}
		
	//returns all the students that need a new card
	@GetMapping("/studentlist")
	public String StudentList(Model model) {
		
		//Change it to a query that returns students with no card
		List<Student> students = studentService.getAllStudents();
		
		model.addAttribute("students", students);
		return "student-list";			
	}
	
	
	@RequestMapping ("/modify_student_search")
	public String ModifyStudent(Model model, @RequestParam Map<String, String> params) {
		
		//checks if the student was updated when the StudentModified was called
		if (params.containsKey("studentUpdated")) 
			model.addAttribute("studentUpdated", Boolean.parseBoolean(params.get("studentUpdated")));
	
		//add check if needed for username
		Student student;
		student = studentService.searchForStudent(params.get("username"));
		
		model.addAttribute("student", student);
		model.addAttribute("mod_student", new ModUserHelper(params.get("username")));
		return "modify-student";	
	}
	
	@PostMapping ("/modify_student/{username}")
	public String StudentModified(@ModelAttribute("mod_student") ModUserHelper modStudent, @PathVariable("username") String username) {
		
		userService.updateUser(modStudent);
		// make redirection string when finished
		String userUpdated = "true";
		try {
			username = URLEncoder.encode(username, "UTF-8"); //encode username to UTF-8 so usernames with greek characters are supported in redirect link
		} catch (UnsupportedEncodingException e) {
			userUpdated = "false";
			e.printStackTrace();
		}
		
		String redStr = "redirect:/clerk/modify_student_search?"
						+ "username=" + username
						+ "&studentUpdated=";
		return  redStr + userUpdated;
		
	}
	
	@PostMapping ("/update_student_limit")
	public String updateStudentLimit(@RequestParam Map<String, String> params) {
		String newlimit;
		boolean studentLimitUpdated;
		
		//checks if params contain limit and the updateStudentLimit returns true/false 
		if (params.containsKey("limit")) { 
			newlimit = params.get("limit");
			studentLimitUpdated = Utilities.updateStudentLimit(newlimit);
		} else studentLimitUpdated = false;
		
		String redStr = "redirect:/clerk?" + "&studentLimitUpdated=";
		return redStr + studentLimitUpdated;
	}
	
	
	
//	/**
//	 * @return change-student-limit
//	 */
//	
//	
//	
//	@GetMapping("/change-student-limit")
//	public String ChangeLimit(Model model) {
//		model.addAttribute("change-student-limit", new StudentLimit());
//		return "change-student-limit";
//	
//	}
//	
//	/**
//	 * @return changed-student-limit
//	 */
//	
//	@PostMapping("/changed-student-limit")
//	public String ChangedLimit(@ModelAttribute StudentLimit limit,ModelMap model) {
//		model.addAttribute("newLimit",limit.getNewLimit());
//		return "changed-student-limit";
//	}
//	
//
//
//	/**
//	 * @return show-student-marking
//	 */
//	
//	@GetMapping("/student-marking")
//	public String CardCreated(Model model) {
//	String marking = "50";
//	model.addAttribute("marking", marking);
//	return "show-student-marking";
//		
//	}
	
}
