package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.user.User;

public interface StudentDAO {
	public List<Student> getAllStudents();
	public void createStudentFromUser(User user);
	public void saveStudent(Student student);
}
