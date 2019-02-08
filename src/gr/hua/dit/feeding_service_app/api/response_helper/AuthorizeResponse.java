package gr.hua.dit.feeding_service_app.api.response_helper;

public class AuthorizeResponse {

	private String username;
	private boolean authenticated;
	private boolean isStudent;

	public AuthorizeResponse() {

	}
//	
//	public AuthorizeResponse(String username) {
//		this.authenticated = false;
//		this.username = username;
//		this.isStudent = false;
//
//	}

	public AuthorizeResponse(String username, boolean authenticated, boolean isStudent) {
		this.username = username;
		this.authenticated = authenticated;
		this.isStudent = isStudent;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAuthenticated() {
		return authenticated;
	}

	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

	public boolean isStudent() {
		return isStudent;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

}
