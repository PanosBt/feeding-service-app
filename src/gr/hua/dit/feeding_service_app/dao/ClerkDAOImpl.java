package gr.hua.dit.feeding_service_app.dao;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

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
	public Clerk getClerk(String username) {
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

	@Override
	public boolean update(ModUserHelper modUser) {
		Clerk clerk;

		if ((clerk = getClerk(modUser.getUsername())) == null)
			return false;
		
		fetchModUserToClerk(modUser, clerk);
		
		sessionFactory.getCurrentSession()
		.update(clerk);
		
		return true;
		
	}
	
	private void fetchModUserToClerk(ModUserHelper modUser, Clerk clerk) {

		if (!StringUtils.isBlank(modUser.getUsername()))
			clerk.setUsername(modUser.getUsername());

		if (!StringUtils.isBlank(modUser.getFirstName()))
			clerk.setFirstName(modUser.getFirstName());

		if (!StringUtils.isBlank(modUser.getLastName()))
			clerk.setLastName(modUser.getLastName());

		if (!StringUtils.isBlank(modUser.getDateOfBirth()))
			clerk.setDateOfBirth(modUser.getDateOfBirthAsDate());

		if (!StringUtils.isBlank(modUser.getIdentityCardNO()))
			clerk.setIdentityCardNO(modUser.getIdentityCardNO());

		if (!StringUtils.isBlank(modUser.getEmail()))
			clerk.setEmail(modUser.getEmail());

		if (!StringUtils.isBlank(modUser.getPhone()))
			clerk.setPhone(modUser.getPhone());

		if (!StringUtils.isBlank(modUser.getSupervising_dept()))
			clerk.setSupervising_dept(modUser.getSupervising_dept());
	}

	@Override
	public void delete(Clerk clerk) {
		sessionFactory.getCurrentSession()
		.delete(clerk);
		
	}

}
