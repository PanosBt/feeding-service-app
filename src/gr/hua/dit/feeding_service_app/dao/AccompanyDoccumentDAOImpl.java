package gr.hua.dit.feeding_service_app.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;

@Repository
public class AccompanyDoccumentDAOImpl implements AccompanyDoccumentDAO {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<AccompanyingDocument> getAccompanyingDocuments(int appl_id) {
		
		Session curSession = sessionFactory.getCurrentSession();
		return curSession
				.createQuery("from AccompanyingDocument WHERE appl_id = :appl_id", AccompanyingDocument.class)
				.setParameter("appl_id", appl_id)
				.getResultList();
		
//		return query.getResultList();
		
	}

	@Override
	public Integer save(AccompanyingDocument accDoc) {
		return (Integer) sessionFactory.getCurrentSession()
		.save(accDoc);
		
	}

}
