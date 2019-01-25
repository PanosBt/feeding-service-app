package gr.hua.dit.feeding_service_app.dao;

import gr.hua.dit.feeding_service_app.entities.Clerk;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

public interface ClerkDAO {
	public void createClerk(String username);
	public Clerk searchForClerk(String username);
	public int delete(String username);
	
	public boolean update(ModUserHelper modUser);
}
