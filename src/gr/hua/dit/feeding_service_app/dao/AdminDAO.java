package gr.hua.dit.feeding_service_app.dao;

import gr.hua.dit.feeding_service_app.entities.Admin;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

public interface AdminDAO {
	
	public void createAdmin(String username);
	public Admin searchForAdmin(String username);
	public int delete(String username);
	
	public boolean update(ModUserHelper modUser);

}
