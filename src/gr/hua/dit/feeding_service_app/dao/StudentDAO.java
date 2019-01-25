package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

public interface StudentDAO {
	public List<Student> getAllStudents();
	public void createStudent(String username);
	public Student searchForStudent(String username);
	public int delete(String username);
	
	public boolean update(ModUserHelper modUser);
}
