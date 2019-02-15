package gr.hua.dit.feeding_service_app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.StudentLimitDAO;
import gr.hua.dit.feeding_service_app.entities.StudentLimit;

@Service
public class StudentLimitServiceImpl implements StudentLimitService {
	
	@Autowired
	StudentLimitDAO studentLimitDAO;

	@Override
	@Transactional
	public StudentLimit getStudentLimit() {
		return studentLimitDAO.getStudentLimit();
	}

	@Override
	@Transactional
	public void update(StudentLimit studentLimit) {
		studentLimitDAO.update(studentLimit);

	}

}
