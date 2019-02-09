package gr.hua.dit.feeding_service_app.api.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.services.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentRestController {
	
	@Autowired
	StudentService studentService;
	
	@GetMapping("/{username}")
	public Student getStudent(@PathVariable String username) {
		Student student;
		if ((student = studentService.getStudent(username)) == null)
			return null;
		else
			return student;
		
		
	}
	
}
