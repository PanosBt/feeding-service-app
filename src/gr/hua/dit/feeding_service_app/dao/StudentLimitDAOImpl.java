package gr.hua.dit.feeding_service_app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.StudentLimit;

@Repository
public class StudentLimitDAOImpl implements StudentLimitDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public StudentLimit getStudentLimit() {
		return sessionFactory.getCurrentSession()
		.get(StudentLimit.class, 1);
	}

	@Override
	public void update(StudentLimit studentLimit) {
		sessionFactory.getCurrentSession()
		.update(studentLimit);

	}

}
