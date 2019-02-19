package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
	
	@Override
	public List<StudentLimit> getAllStudentLimits() {
		
		Session curSession = sessionFactory.getCurrentSession();
		
		Query<StudentLimit> query = curSession.createQuery("from StudentLimit", StudentLimit.class);
		
		return query.getResultList();
	}

	@Override
	public StudentLimit getStudentLimitOf(String dept) {
		return sessionFactory.getCurrentSession()
				.createQuery("from StudentLimit WHERE dept = :dept", StudentLimit.class)
				.setParameter("dept", dept)
				.getSingleResult();
	}

}
