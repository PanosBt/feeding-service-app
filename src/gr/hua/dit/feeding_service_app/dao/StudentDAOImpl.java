package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public List<Student> getAllStudents() {

		Session curSession = sessionFactory.getCurrentSession();

		Query<Student> query = curSession.createQuery("from Student", Student.class);
		return query.getResultList();
	}

	// We will probably do this in a different way after we learn Services @ the lab
	// TODO remove when tests not needed
	@Override
	@Transactional
	public void saveStudent(Student student) {
		Session curSession = sessionFactory.getCurrentSession();
		curSession.save(student);
	}

	@Override
	public void createStudent(String username) {
		sessionFactory.getCurrentSession()
		.save(new Student(username));

	}

	@Override
	public Student searchForStudent(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("FROM Student WHERE username = :username", Student.class)
				.setParameter("username", username)
				.uniqueResult();
	}

	@Override
	public int delete(String username) {
		return sessionFactory.getCurrentSession()
				.createQuery("DELETE FROM Student WHERE username = :username")
				.setParameter("username", username)
				.executeUpdate();
	}

}
