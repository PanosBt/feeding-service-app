package gr.hua.dit.feeding_service_app.services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.ApplicationDAO;
import gr.hua.dit.feeding_service_app.dao.StudentDAO;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.utilites.Utilities;

@Service
public class ApplicationServiceImpl implements ApplicationService {
	
	@Autowired
	private ApplicationDAO applicationDAO;
	
	@Autowired
	private StudentDAO studentDAO;
	
	
	@Override
	@Transactional
	public List<Application> getUncheckedApplicationsByDpt(String dept) {
		List<Application> uncheckedApplicationsList = new ArrayList<Application>();
		//Get all students from the dept to check their applications
		List<Student> students = studentDAO.getStudentsByDpt(dept);
		
		
		//for each student checks its applications if its already checked and the date os submision
		for (Student student: students) {
			 List<Application> applications = student.getApplications();		 
			 for(Application application: applications) {
				 Calendar cal = Calendar.getInstance();
				 if (application.isApproved() == null && Utilities.getYearFromDate(application.getSubm_date()) == cal.get(Calendar.YEAR)) {	 
					 uncheckedApplicationsList.add(application);
				 }
			 }	
		}	
		return uncheckedApplicationsList;
	}
	
	@Override
	@Transactional
	public Application getApplication(int appl_id) {
		
		return applicationDAO.getApplication(appl_id);
	}
	
	@Override
	@Transactional
	public void update(Application application) {
		
		applicationDAO.update(application);
		
	}

	@Override
	@Transactional
	public int getRank(Application application) {
		int year = Utilities.getYearFromDate(application.getSubm_date());
		List<Application> applications = applicationDAO.getApplicationsByYearOrderedByRank(year);
		//TODO Reconsider the above TOO MUCH HEAVY QUERIES!!!
		for (Application app : applications)
			if (app.getAppl_id() == application.getAppl_id())
				return applications.indexOf(app) + 1;
		return 0;
	}

	@Override
	@Transactional
	public Integer save(Application application) {
		
		return applicationDAO.save(application);
		
	}

}
