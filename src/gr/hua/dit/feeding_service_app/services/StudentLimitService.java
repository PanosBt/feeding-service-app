package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.StudentLimit;

public interface StudentLimitService {
	public StudentLimit getStudentLimit();
	public void update(StudentLimit studentLimit);
	public List<StudentLimit> getAllStudentLimits();
}
