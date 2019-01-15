package gr.hua.dit.feeding_service_app.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.user.User;

@Repository
public class ClerkDAOImpl implements ClerkDAO {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createClerkFromUser(User user) {
		Session curSession = sessionFactory.getCurrentSession();

		curSession.save(new Clerk(user.getUsername(), user.getPassword()));

	}

}
