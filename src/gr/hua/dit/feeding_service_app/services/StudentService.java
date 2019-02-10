package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;

public interface StudentService {
	
	public Student getStudent(String username);
	
	public List<Student> getAllStudents();

	public Student getStudent(int id);
	
	public void update(Student student);

}
