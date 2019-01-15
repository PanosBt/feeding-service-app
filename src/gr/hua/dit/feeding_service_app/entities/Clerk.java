package gr.hua.dit.feeding_service_app.entities;

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
@Table(name = "clerk")
public class Clerk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

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

	@Column(name = "supervising_dept")
	private String supervising_dept;

	public Clerk() {
	}

	public Clerk(String username, String pass, String firstName, String lastName, Date dateOfBirth,
			String identityCardNO, String email, String phone, String supervising_dept) {
		this.username = username;
		this.pass = pass;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.identityCardNO = identityCardNO;
		this.email = email;
		this.phone = phone;
		this.supervising_dept = supervising_dept;
	}

	public Clerk(String username, String pass) {
		this.username = username;
		this.pass = pass;
	}

	public int getId() {
		return id;
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

	public String getSupervising_dept() {
		return supervising_dept;
	}

	public void setSupervising_dept(String supervising_dept) {
		this.supervising_dept = supervising_dept;
	}

	@Override
	public String toString() {
		return "Clerk [id=" + id + ", username=" + username + ", pass=" + pass + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", dateOfBirth=" + dateOfBirth + ", identityCardNO=" + identityCardNO
				+ ", email=" + email + ", phone=" + phone + ", supervising_dept=" + supervising_dept + "]";
	}

}
