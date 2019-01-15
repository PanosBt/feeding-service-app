package gr.hua.dit.feeding_service_app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.ClerkDAO;
import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.user.User;

@Service
public class UserServiceImp implements UserService {
	
	@Autowired
	private StudentDAO studentDAO;
	
	@Autowired
	private ClerkDAO clerkDAO;

	@Override
	@Transactional
	public void createUser(User user) {
		//TODO check if username exists
			
		if (user.getUserType().equals("student"))
			studentDAO.createStudentFromUser(user);

		if (user.getUserType().equals("clerk")) 
			clerkDAO.createClerkFromUser(user);
		
	}

}
