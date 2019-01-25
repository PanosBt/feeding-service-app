package gr.hua.dit.feeding_service_app.model_helper;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Helper class for admin modify-user form
 * @author panos
 *
 */
public class ModUserHelper {

	private String username;

	private String firstName;

	private String lastName;

	private String dateOfBirth;
	
	private String identityCardNO;

	private String email;

	private String phone;
	
	private String supervising_dept;
	
	private int academicID;

	private String dept;

	public ModUserHelper(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getIdentityCardNO() {
		return identityCardNO;
	}

	public void setIdentityCardNO(String identityCardNO) {
		this.identityCardNO = identityCardNO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSupervising_dept() {
		return supervising_dept;
	}

	public void setSupervising_dept(String supervising_dept) {
		this.supervising_dept = supervising_dept;
	}

	public int getAcademicID() {
		return academicID;
	}

	public void setAcademicID(int academicID) {
		this.academicID = academicID;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}
	
	public Date getDateOfBirthAsDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		// see https://stackoverflow.com/questions/22929237/convert-java-time-localdate-into-java-util-date-type
		return Date.from(
				LocalDate.parse(dateOfBirth, dtf)
				.atStartOfDay(ZoneId.systemDefault())
				.toInstant()
				);
	}
	
}
