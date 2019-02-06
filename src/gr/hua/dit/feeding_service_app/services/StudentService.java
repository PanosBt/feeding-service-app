package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;

public interface StudentService {
	
	public Student searchForStudent(String username);
	
	public List<Student> getAllStudents();
}