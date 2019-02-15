package gr.hua.dit.feeding_service_app.services;

import gr.hua.dit.feeding_service_app.entities.StudentLimit;

public interface StudentLimitService {
	public StudentLimit getStudentLimit();
	public void update(StudentLimit studentLimit);
}
