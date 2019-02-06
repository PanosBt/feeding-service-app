package gr.hua.dit.feeding_service_app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public User searchForUser(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM User WHERE username = :username", User.class)
				.setParameter("username", username)
				.uniqueResult();
	}

	@Override
	public void createUser(User user) {
		sessionFactory.getCurrentSession()
		.save(user);

	}

	@Override
	public void delete(User user) {
			sessionFactory.getCurrentSession().delete(user);
		
	}

}