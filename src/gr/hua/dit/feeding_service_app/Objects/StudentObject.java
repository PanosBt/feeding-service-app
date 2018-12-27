package gr.hua.dit.feeding_service_app.Objects;

public class StudentObject {
	String firstName;
	String lastName;
	String academicID;
	String identityCardNO;
	String phone;
	String email;
	String dept;
	
	
	
	public StudentObject() {
		super();
	}
	public StudentObject(String firstName, String lastName, String academicID, String identityCardNO, String phone,
			String email, String dept) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.academicID = academicID;
		this.identityCardNO = identityCardNO;
		this.phone = phone;
		this.email = email;
		this.dept = dept;
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
	public String getAcademicID() {
		return academicID;
	}
	public void setAcademicID(String academicID) {
		this.academicID = academicID;
	}
	public String getIdentityCardNO() {
		return identityCardNO;
	}
	public void setIdentityCardNO(String identityCardNO) {
		this.identityCardNO = identityCardNO;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	
	
	

}
