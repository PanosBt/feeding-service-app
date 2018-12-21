package gr.hua.dit.feeding_service_app.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.entity.Student;
import gr.hua.dit.feeding_service_app.utilites.Utilities;

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
	
	@RequestMapping("/addtest")
	public String insertTestStudent() {
		
		Date date;
		try {
			date = Utilities.parseDate("01/05/2018");
		} catch (ParseException e) {
			e.printStackTrace();
			return "redirect:/customer/list";
		}
		Student student = new Student("test1", "test", "test", "test", date, "test", "test", "test", 123, "dit");
		studentDAO.saveStudent(student);	
		return "redirect:/student/list";
	}
}
