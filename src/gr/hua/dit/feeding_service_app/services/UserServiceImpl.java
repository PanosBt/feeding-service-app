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
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;
import gr.hua.dit.feeding_service_app.model_helper.NewUserHelper;
import gr.hua.dit.feeding_service_app.utilites.CustomAuthorityUtilities;

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
	public boolean createUser(NewUserHelper newUser) {

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

		if (userDAO.getUser(newUser.getUsername()) != null)
			return false;

		// create and save new enabled user
		User user = new User(newUser.getUsername(), encoder.encode(newUser.getPassword()), true);
		userDAO.createUser(user);
//		System.out.println("Password is: " + newUser.getPassword());
		
		// create new table tuple and new authorities based on user type
		switch (newUser.getUserType()) {
		case "student":
			autorityDAO.createAuthority(new Authority(user, CustomAuthorityUtilities.STUDENT_ROLE));
			studentDAO.createStudent(newUser.getUsername(), newUser.getDept());
			break;
		case "clerk":
			autorityDAO.createAuthority(new Authority(user, CustomAuthorityUtilities.CLERK_ROLE));
			clerkDAO.createClerk(newUser.getUsername());
			break;
		case "supervisor":
			autorityDAO.createAuthority(new Authority(user, CustomAuthorityUtilities.CLERK_ROLE));
			autorityDAO.createAuthority(new Authority(user, CustomAuthorityUtilities.SUPERVISOR_ROLE));
			clerkDAO.createClerk(newUser.getUsername());
			break;
		case "admin":
			autorityDAO.createAuthority(new Authority(user, CustomAuthorityUtilities.ADMIN_ROLE));
			adminDAO.createAdmin(newUser.getUsername());
			break;
		default:
			return false;
		}

		return true;

	}

	@Override
	@Transactional
	public User searchUser(String username) {
		return userDAO.getUser(username);

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
			return authorities.get(0).getAuthority().equals(CustomAuthorityUtilities.SUPERVISOR_ROLE)
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
		case CustomAuthorityUtilities.ADMIN_ROLE:
			adminDAO.delete(username);
			break;
		case CustomAuthorityUtilities.CLERK_ROLE:
		case CustomAuthorityUtilities.SUPERVISOR_ROLE:
			clerkDAO.delete(username);
			break;
		case CustomAuthorityUtilities.STUDENT_ROLE:
			studentDAO.delete(username);
			break;
		default:
			return false;
		}
		
		return true;
	}

	@Override
	@Transactional
	public void updateUser(ModUserHelper modUser) {
		
		String role = getUserHigherRole(modUser.getUsername());
		
		switch (role) {
		case CustomAuthorityUtilities.ADMIN_ROLE:
			adminDAO.update(modUser);
			break;
		case CustomAuthorityUtilities.CLERK_ROLE:
		case CustomAuthorityUtilities.SUPERVISOR_ROLE:
			clerkDAO.update(modUser);
			break;
		case CustomAuthorityUtilities.STUDENT_ROLE:
			studentDAO.update(modUser);
			break;
		default:
			//return false;
		}
		
	}

	@Override
	@Transactional
	public boolean authenticateUser(String username, String pass) {
		return userDAO.authenticateUser(username, pass);
		
	}
	


}
