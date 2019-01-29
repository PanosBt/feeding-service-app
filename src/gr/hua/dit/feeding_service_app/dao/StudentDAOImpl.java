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

	//TODO Probably not needed, delete it if that's so
	@Override
	@Transactional
	public List<Student> getAllStudents() {

		Session curSession = sessionFactory.getCurrentSession();

		Query<Student> query = curSession.createQuery("from Student", Student.class);
		return query.getResultList();
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

	@Override
	public boolean update(ModUserHelper modUser) {
		Student student;

		if ((student = searchForStudent(modUser.getUsername())) == null)
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

	}


}
