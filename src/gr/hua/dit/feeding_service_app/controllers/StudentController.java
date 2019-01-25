package gr.hua.dit.feeding_service_app.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import gr.hua.dit.feeding_service_app.dao.StudentDAO;
<<<<<<< HEAD:src/gr/hua/dit/feeding_service_app/controller/StudentController.java
import gr.hua.dit.feeding_service_app.dao.StudentDAOImpl;
import gr.hua.dit.feeding_service_app.entity.Student;
=======
import gr.hua.dit.feeding_service_app.entities.Student;
>>>>>>> 189e3e0a5d168a2c5fc2806d621061110db5f53d:src/gr/hua/dit/feeding_service_app/controllers/StudentController.java
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
	
//	@RequestMapping("/addtest")
//	public String insertTestStudent() {
//		
//		Date date;
//		try {
//			date = Utilities.parseDate("01/05/2018");
//		} catch (ParseException e) {
//			e.printStackTrace();
//			return "redirect:/customer/list";
//		}
//		Student student = new Student("test4", "test", "test", "test", date, "test", "test", "test", 123, "dit");
//		studentDAO.saveStudent(student);	
//		return "redirect:/student/list";
//	}
}
