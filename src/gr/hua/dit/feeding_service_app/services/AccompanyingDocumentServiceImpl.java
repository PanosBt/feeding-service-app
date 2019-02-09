package gr.hua.dit.feeding_service_app.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gr.hua.dit.feeding_service_app.dao.AccompanyDoccumentDAO;
import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;

@Service
public class AccompanyingDocumentServiceImpl implements AccompanyingDocumentService {
	
	@Autowired
	private AccompanyDoccumentDAO accompanyDoccumentDAO;

	@Override
	@Transactional
	public List<AccompanyingDocument> getAccompanyingDocuments(int appl_id) {
	
		return accompanyDoccumentDAO.getAccompanyingDocuments(appl_id);
	}

}
