package gr.hua.dit.feeding_service_app.dao;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;

public interface AccompanyDoccumentDAO {
	
	public List<AccompanyingDocument> getAccompanyingDocuments(int appl_id);

	public Integer save(AccompanyingDocument accDoc);
}
