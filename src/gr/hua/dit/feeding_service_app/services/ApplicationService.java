package gr.hua.dit.feeding_service_app.services;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.Application;

public interface ApplicationService {
	
	public List<Application> getUncheckedApplicationsByDpt(String dept);
	
	public Application getApplication(int appl_id);
	public void updateApplication(Application application);

	public int getRank(Application application);

	public void save(Application application);
}
