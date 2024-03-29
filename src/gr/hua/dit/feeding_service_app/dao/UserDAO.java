package gr.hua.dit.feeding_service_app.dao;

import gr.hua.dit.feeding_service_app.entities.User;

public interface UserDAO {
	/**
	 * Returns the user w/ the given username or null if no 
	 * user w/ such username exists
	 * @param username
	 * @return the corresponding User or null
	 */
	public User getUser(String username);
	
	public void createUser(User user);
	public void delete(User user);

	public boolean authenticateUser(String username, String password);
}
