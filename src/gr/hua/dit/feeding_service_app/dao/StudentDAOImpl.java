package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entity.Student;

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

}
