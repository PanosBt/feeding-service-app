package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.model_helper.ModUserHelper;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override
	@Transactional
	public List<Student> getStudentsWithNoData(String dept) {

		Session curSession = sessionFactory.getCurrentSession();

		Query<Student> query = curSession.createQuery("from Student WHERE data_init = false AND dept = :dept", Student.class)
										 .setParameter("dept", dept);
		return query.getResultList();
	}
	
	@Override
	public List<Student> getStudentsByDpt(String dept) {
		
		Session curSession = sessionFactory.getCurrentSession();
		Query<Student> query = curSession.createQuery("from Student WHERE dept = :dept", Student.class)
										 .setParameter("dept", dept);
		
		return query.getResultList();
	}

	@Override
	public void createStudent(String username, String dept) {
		sessionFactory.getCurrentSession()
		.save(new Student(username, dept));

	}
	

	@Override
	public Student getStudent(int id) {
		return sessionFactory.getCurrentSession()
				.get(Student.class, id);
	}

	@Override
	public Student getStudent(String username) {
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
	

	@Override
	public void update(Student student) {
		sessionFactory.getCurrentSession()
		.update(student);
		
	}

	@Override
	public boolean update(ModUserHelper modUser) {
		Student student;

		if ((student = getStudent(modUser.getUsername())) == null)
			return false;
		
		fetchModUserToStudent(modUser, student);
		
		sessionFactory.getCurrentSession()
		.update(student);
		
		return true;
		
	}
	
	private void fetchModUserToStudent(ModUserHelper modUser, Student student) {

		if (!StringUtils.isBlank(modUser.getUsername()))
			student.setUsername(modUser.getUsername());

		if (!StringUtils.isBlank(modUser.getFirstName()))
			student.setFirstName(modUser.getFirstName());

		if (!StringUtils.isBlank(modUser.getLastName()))
			student.setLastName(modUser.getLastName());

		if (!StringUtils.isBlank(modUser.getDateOfBirth()))
			student.setDateOfBirth(modUser.getDateOfBirthAsDate());

		if (!StringUtils.isBlank(modUser.getIdentityCardNO()))
			student.setIdentityCardNO(modUser.getIdentityCardNO());

		if (!StringUtils.isBlank(modUser.getEmail()))
			student.setEmail(modUser.getEmail());

		if (!StringUtils.isBlank(modUser.getPhone()))
			student.setPhone(modUser.getPhone());
		
		if (modUser.getAcademicID() > 0)
			student.setAcademicID(modUser.getAcademicID());
		
		if (!StringUtils.isBlank(modUser.getDept()))
			student.setDept(modUser.getDept());
		
//		student.setData_init(modUser.getData_init());
	}

	@Override
	public void delete(Student student) {
		if (student != null)
			sessionFactory.getCurrentSession()
			.delete(student);
	}






}
