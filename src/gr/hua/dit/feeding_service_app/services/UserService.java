package gr.hua.dit.feeding_service_app.services;

import gr.hua.dit.feeding_service_app.entities.User;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;
import gr.hua.dit.feeding_service_app.model_helper.NewUserHelper;

public interface UserService {
	public boolean createUser(NewUserHelper newUser);
	public User searchUser(String username);
	
	/**
	 * Returns the most important role of a user
	 * i.e. the role of a user or SUPERVISOR_ROLE if user
	 * has both SUPERVISOR_ROLE and CLERK_ROLE
	 * or null if user doesn't exist or has no role
	 * @param username
	 * @return
	 */
	public String getUserHigherRole(String username);
	
	public boolean deleteUser(String username);
	
	public void updateUser(ModUserHelper modUser);
}
