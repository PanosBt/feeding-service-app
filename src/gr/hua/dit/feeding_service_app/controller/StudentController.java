package gr.hua.dit.feeding_service_app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.entity.Student;

@Controller
@RequestMapping("/student")
public class StudentController {
		
	@Autowired
	private StudentDAO studentDAO;
	
	@RequestMapping("/list")
	public String testListStudents(Model model) {
		
		List<Student> students = studentDAO.getAllStudents();
		model.addAttribute("students", students);
		return "list-students";
	}
}
