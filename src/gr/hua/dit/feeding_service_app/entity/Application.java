package gr.hua.dit.feeding_service_app.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "application")
public class Application {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appl_id")
	private Integer appl_id;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private Student student;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "clerk_checked_id")
	private Clerk clerk;

	@Column(name = "familyIncome")
	private int familyIncome;

	@Column(name = "num_siblings")
	private int num_siblings;

	@Column(name = "origin_city")
	private String origin_city;

	@Column(name = "mother_employeed")
	private boolean mother_employeed;

	@Column(name = "father_employeed")
	private boolean father_employeed;

	@Column(name = "approved")
	private boolean approved;

	@Column(name = "score")
	private int score;

	public Application() {
	}

	public Application(Student student, Clerk clerk, int familyIncome, int num_siblings, String origin_city,
			boolean mother_employeed, boolean father_employeed, boolean approved, int score) {
		this.appl_id = null;
		this.student = student;
		this.clerk = clerk;
		this.familyIncome = familyIncome;
		this.num_siblings = num_siblings;
		this.origin_city = origin_city;
		this.mother_employeed = mother_employeed;
		this.father_employeed = father_employeed;
		this.approved = approved;
		this.score = score;
	}

	public Application(Student student, int familyIncome, int num_siblings, String origin_city,
			boolean mother_employeed, boolean father_employeed) {
		this.appl_id = null;
		this.student = student;
		this.familyIncome = familyIncome;
		this.num_siblings = num_siblings;
		this.origin_city = origin_city;
		this.mother_employeed = mother_employeed;
		this.father_employeed = father_employeed;
	}

	public Integer getAppl_id() {
		return appl_id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}

	public int getFamilyIncome() {
		return familyIncome;
	}

	public void setFamilyIncome(int familyIncome) {
		this.familyIncome = familyIncome;
	}

	public int getNum_siblings() {
		return num_siblings;
	}

	public void setNum_siblings(int num_siblings) {
		this.num_siblings = num_siblings;
	}

	public String getOrigin_city() {
		return origin_city;
	}

	public void setOrigin_city(String origin_city) {
		this.origin_city = origin_city;
	}

	public boolean isMother_employeed() {
		return mother_employeed;
	}

	public void setMother_employeed(boolean mother_employeed) {
		this.mother_employeed = mother_employeed;
	}

	public boolean isFather_employeed() {
		return father_employeed;
	}

	public void setFather_employeed(boolean father_employeed) {
		this.father_employeed = father_employeed;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "Application [appl_id=" + appl_id + ", student=" + student + ", clerk=" + clerk + ", familyIncome="
				+ familyIncome + ", num_siblings=" + num_siblings + ", origin_city=" + origin_city
				+ ", mother_employeed=" + mother_employeed + ", father_employeed=" + father_employeed + ", approved="
				+ approved + ", score=" + score + "]";
	}

}
