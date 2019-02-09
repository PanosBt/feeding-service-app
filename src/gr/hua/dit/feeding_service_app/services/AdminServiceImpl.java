package gr.hua.dit.feeding_service_app.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.AdminDAO;
import gr.hua.dit.feeding_service_app.entities.Admin;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	@Transactional
	public Admin getAdmin(String username) {
		return adminDAO.getAdmin(username);
	}

}
