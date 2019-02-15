package gr.hua.dit.feeding_service_app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_limit")
public class StudentLimit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@Column(name = "student_limit")
	private int student_limit;

	public StudentLimit() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStudent_limit() {
		return student_limit;
	}

	public void setStudent_limit(int student_limit) {
		this.student_limit = student_limit;
	}

}
