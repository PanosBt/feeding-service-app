package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.ApplicationDAO;
import gr.hua.dit.feeding_service_app.entities.Application;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private ApplicationDAO applicationdao;
	
	@Override
	@Transactional
	public List<Application> getAllApplications() {
		return applicationdao.getAllApplications();
	}
	
	@Override
	@Transactional
	public Application getApplication(int appl_id) {
		
		return applicationdao.getApplication(appl_id);
	}
	
	@Override
	@Transactional
	public void updateApplication(Application application) {
		
		applicationdao.updateApplication(application);
		
	}

}
