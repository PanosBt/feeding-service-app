package gr.hua.dit.feeding_service_app.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Admin;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

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

	@Override
	public boolean update(ModUserHelper modUser) {
		Admin admin;

		if ((admin = searchForAdmin(modUser.getUsername())) == null)
			return false;
		
		fetchModUserToAdmin(modUser, admin);
		
		sessionFactory.getCurrentSession()
		.update(admin);
		
		return true;
		
	}
	
	private void fetchModUserToAdmin(ModUserHelper modUser, Admin admin) {

		if (!StringUtils.isBlank(modUser.getUsername()))
			admin.setUsername(modUser.getUsername());

		if (!StringUtils.isBlank(modUser.getFirstName()))
			admin.setFirstName(modUser.getFirstName());

		if (!StringUtils.isBlank(modUser.getLastName()))
			admin.setLastName(modUser.getLastName());

		if (!StringUtils.isBlank(modUser.getDateOfBirth()))
			admin.setDateOfBirth(modUser.getDateOfBirthAsDate());

		if (!StringUtils.isBlank(modUser.getIdentityCardNO()))
			admin.setIdentityCardNO(modUser.getIdentityCardNO());

		if (!StringUtils.isBlank(modUser.getEmail()))
			admin.setEmail(modUser.getEmail());

		if (!StringUtils.isBlank(modUser.getPhone()))
			admin.setPhone(modUser.getPhone());

	}

}
