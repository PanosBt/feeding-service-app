package gr.hua.dit.feeding_service_app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.ClerkDAO;
import gr.hua.dit.feeding_service_app.entities.Clerk;

@Service
public class ClerkServiceImpl implements ClerkService {
	
	@Autowired
	private ClerkDAO clerkDAO;

	@Override
	@Transactional
	public Clerk getClerk(String username) {
		return clerkDAO.getClerk(username);
	}

}
