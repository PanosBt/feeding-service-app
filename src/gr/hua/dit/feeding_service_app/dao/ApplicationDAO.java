package gr.hua.dit.feeding_service_app.dao;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.Application;

public interface ApplicationDAO {

	public List<Application> getAllApplications();
	
//	public Application searchApplication(int appl_id);
}
