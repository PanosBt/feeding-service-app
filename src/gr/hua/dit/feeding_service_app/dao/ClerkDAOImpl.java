package gr.hua.dit.feeding_service_app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Clerk;

@Repository
public class ClerkDAOImpl implements ClerkDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createClerk(String username) {
		sessionFactory.getCurrentSession()
		.save(new Clerk(username));

	}

	@Override
	public Clerk searchForClerk(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Clerk WHERE username = :username", Clerk.class)
				.setParameter("username", username)
				.uniqueResult();
	}

	@Override
	public int delete(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Clerk WHERE username = :username")
				.setParameter("username", username)
				.executeUpdate();
	}

}