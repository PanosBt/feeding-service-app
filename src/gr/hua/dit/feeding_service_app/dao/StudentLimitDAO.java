package gr.hua.dit.feeding_service_app.dao;

import gr.hua.dit.feeding_service_app.entities.StudentLimit;

public interface StudentLimitDAO {
	public StudentLimit getStudentLimit();
	public void update(StudentLimit studentLimit);
	
}
