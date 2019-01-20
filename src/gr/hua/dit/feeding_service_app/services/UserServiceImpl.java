package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.AdminDAO;
import gr.hua.dit.feeding_service_app.dao.AuthorityDAO;
import gr.hua.dit.feeding_service_app.dao.ClerkDAO;
import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.dao.UserDAO;
import gr.hua.dit.feeding_service_app.entities.Authority;
import gr.hua.dit.feeding_service_app.entities.User;
import gr.hua.dit.feeding_service_app.utilites.AuthorityUtilities;
import gr.hua.dit.feeding_service_app.utilites.NewUserUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private AuthorityDAO autorityDAO;
	@Autowired
	private StudentDAO studentDAO;
	@Autowired
	private ClerkDAO clerkDAO;
	@Autowired
	private AdminDAO adminDAO;

	@Override
	@Transactional
	public boolean createUser(NewUserUtil newUser) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

		if (userDAO.searchForUser(newUser.getUsername()) != null)
			return false;

		// create and save new enabled user
		User user = new User(newUser.getUsername(), encoder.encode(newUser.getPassword()), true);
		userDAO.createUser(user);
//		System.out.println("Password is: " + newUser.getPassword());

		// create new table tuple and new authorities based on user type
		switch (newUser.getUserType()) {
		case "student":
			autorityDAO.createAuthority(new Authority(user, AuthorityUtilities.STUDENT_ROLE));
			studentDAO.createStudent(newUser.getUsername());
			break;
		case "clerk":
			autorityDAO.createAuthority(new Authority(user, AuthorityUtilities.CLERK_ROLE));
			clerkDAO.createClerk(newUser.getUsername());
			break;
		case "supervisor":
			autorityDAO.createAuthority(new Authority(user, AuthorityUtilities.CLERK_ROLE));
			autorityDAO.createAuthority(new Authority(user, AuthorityUtilities.SUPERVISOR_ROLE));
			clerkDAO.createClerk(newUser.getUsername());
			break;
		case "admin":
			autorityDAO.createAuthority(new Authority(user, AuthorityUtilities.ADMIN_ROLE));
			adminDAO.createAdmin(newUser.getUsername());
		}

		return true;

	}

	@Override
	@Transactional
	public User searchUser(String username) {
		return userDAO.searchForUser(username);

	}

	@Override
	@Transactional
	public String getUserHigherRole(String username) {
		List<Authority> authorities = autorityDAO.getAuthorities(username);

		switch (authorities.size()) {
		case 1:
			return authorities.get(0).getAuthority();
		// if user is both supervisor and clerk, return supervisor role
		case 2:
			return authorities.get(0).getAuthority().equals(AuthorityUtilities.SUPERVISOR_ROLE)
					? authorities.get(0).getAuthority()
					: authorities.get(1).getAuthority();
		default:
			return null;
		}

	}

	@Override
	@Transactional
	public boolean deleteUser(String username) {
		User user;
		// if user doesn't exist, return false
		if ((user = searchUser(username)) == null)
			return false;
		
		String role = getUserHigherRole(username);
		
		autorityDAO.deleteAuthorities(username);
		userDAO.delete(user);
		
		// delete user from corresponding table based on his/her higher role
		switch (role) {
		case AuthorityUtilities.ADMIN_ROLE:
			adminDAO.delete(username);
			break;
		case AuthorityUtilities.CLERK_ROLE:
		case AuthorityUtilities.SUPERVISOR_ROLE:
			clerkDAO.delete(username);
			break;
		case AuthorityUtilities.STUDENT_ROLE:
			studentDAO.delete(username);
			break;
		default:
			return false;
		}
		
		return true;
	}

}
