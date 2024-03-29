package gr.hua.dit.feeding_service_app.dao;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Clerk;

public interface ApplicationDAO {

	public List<Application> getAllApplications();
	
	public Application getApplication(int appl_id);
	public void update(Application application);

	public List<Application> getApplicationsByYearOrderedByRank(int year);

	public Integer save(Application application);

	public void removeApplicationChecks(Clerk clerk);
}
