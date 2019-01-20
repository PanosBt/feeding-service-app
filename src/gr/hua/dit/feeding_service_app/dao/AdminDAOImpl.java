package gr.hua.dit.feeding_service_app.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Admin;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createAdmin(String username) {
		sessionFactory.getCurrentSession()
		.save(new Admin(username));
		
	}

	@Override
	public Admin searchForAdmin(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Admin WHERE username = :username", Admin.class)
				.setParameter("username", username)
				.uniqueResult();
	}

	@Override
	public int delete(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Admin WHERE username = :username")
				.setParameter("username", username)
				.executeUpdate();
	}


}
