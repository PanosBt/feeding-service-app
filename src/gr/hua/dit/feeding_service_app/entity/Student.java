package gr.hua.dit.feeding_service_app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "username")
	private String username;

	@Column(name = "pass")
	private String pass;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@Column(name = "dateOfBirth")
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "identityCardNO")
	private String identityCardNO;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "academicID")
	private int academicID;

	@Column(name = "dept")
	private String dept;

	public Student() {
	}

	public Student(String username, String pass, String firstName, String lastName, Date dateOfBirth,
			String identityCardNO, String email, String phone, int academicID, String dept) {
		this.id = null;
		this.username = username;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.identityCardNO = identityCardNO;
		this.email = email;
		this.phone = phone;
		this.academicID = academicID;
		this.dept = dept;
	}

	public Student(String username, String pass) {
		this.id = null;
		this.username = username;
		this.pass = pass;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
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

	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", pass=" + pass + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", identityCardNO=" + identityCardNO
				+ ", email=" + email + ", phone=" + phone + ", academicID=" + academicID + ", dept=" + dept + "]";
	}

}
