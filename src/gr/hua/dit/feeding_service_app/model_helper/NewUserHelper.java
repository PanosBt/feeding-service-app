package gr.hua.dit.feeding_service_app.model_helper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Helper class for admin new user form
 * 
 * @author panos
 *
 */
public class NewUserHelper {

	@NotBlank(message = "Please enter a username")
	private String username;

	@NotBlank(message = "Please enter a password")
	@Size(min = 6, max = 15, message = "Your password must be between 6 and 15 characters")
	private String password;

	private String userType;

	private String dept;

	public NewUserHelper() {
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

}
