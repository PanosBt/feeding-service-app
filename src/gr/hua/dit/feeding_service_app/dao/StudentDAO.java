package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;

public interface StudentDAO {
	public List<Student> getAllStudents();
	public void createStudent(String username);
	public void saveStudent(Student student);
	public Student searchForStudent(String username);
	public int delete(String username);
}
