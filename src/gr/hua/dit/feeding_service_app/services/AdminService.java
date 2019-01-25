package gr.hua.dit.feeding_service_app.services;

import gr.hua.dit.feeding_service_app.entities.Admin;

public interface AdminService {
	public Admin searchForAdmin(String username);
}
