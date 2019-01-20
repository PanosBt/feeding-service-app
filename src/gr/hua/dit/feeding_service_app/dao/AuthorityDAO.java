package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import gr.hua.dit.feeding_service_app.entities.Authority;

public interface AuthorityDAO {
	
	public void createAuthority(Authority authority);
	public List<Authority> getAuthorities(String username);
	public int deleteAuthorities(String username);

}
