package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

public interface StudentDAO {
	public List<Student> getStudentsWithNoData(String dept);
	public List<Student> getStudentsByDpt(String dept);
	public void createStudent(String username);
	public Student getStudent(String username);
	public int delete(String username);
	
	public boolean update(ModUserHelper modUser);
	public void update(Student student);
	public Student getStudent(int id);
}
