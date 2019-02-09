package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;

@Repository
public class ApplicationDAOImpl implements ApplicationDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override	
	public List<Application> getAllApplications() {
		Session curSession = sessionFactory.getCurrentSession();
		
		Query<Application> query = curSession.createQuery("from Application WHERE approved IS NULL", Application.class);
		return query.getResultList();
	}
	
	@Override
	public Application getApplication(int appl_id) {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Application WHERE appl_id = :appl_id", Application.class)
				.setParameter("appl_id", appl_id)
				.uniqueResult();
	}
	
	@Override
	public void updateApplication(Application application) {
		
		sessionFactory.getCurrentSession()
		.update(application);
		
	}

}
