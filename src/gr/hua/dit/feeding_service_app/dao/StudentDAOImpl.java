package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.user.User;

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

	//We will probably do this in a different way after we learn Services @ the lab
	// TODO remove when tests not needed
	@Override
	@Transactional
	public void saveStudent(Student student) {
		Session curSession = sessionFactory.getCurrentSession();	
		curSession.save(student);
	}

	@Override
	public void createStudentFromUser(User user) {
		Session curSession = sessionFactory.getCurrentSession();
			
		curSession.save(new Student(user.getUsername(), user.getPassword()));
	}

}
