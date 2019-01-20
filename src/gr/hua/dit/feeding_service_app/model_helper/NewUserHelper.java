package gr.hua.dit.feeding_service_app.model_helper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NewUserHelper {
	
	@NotBlank(message = "Please enter a username")
	private String username;
	
	@NotBlank(message = "Please enter a password")
	@Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters")
	private String password;

	private String userType;

	public NewUserHelper() {
	}

	public NewUserHelper(String username, String password, String userType) {
		this.username = username;
		this.password = password;
		this.userType = userType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", userType=" + userType + "]";
	}

}
