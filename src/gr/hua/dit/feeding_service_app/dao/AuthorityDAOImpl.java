package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Authority;

@Repository
public class AuthorityDAOImpl implements AuthorityDAO {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void createAuthority(Authority authority) {
		sessionFactory.getCurrentSession()
		.save(authority);
	}

	@Override
	public List<Authority> getAuthorities(String username) {

		Query<Authority> query = sessionFactory.getCurrentSession()
				.createQuery("FROM Authority WHERE username = :username", Authority.class)
				.setParameter("username", username);
		return query.getResultList();

	}

	@Override
	public int deleteAuthorities(String username) {
		return sessionFactory.getCurrentSession()
			.createQuery("DELETE FROM Authority WHERE username = :username")
			.setParameter("username", username)
			.executeUpdate();
	}

}
