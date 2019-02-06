package gr.hua.dit.feeding_service_app.dao;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;

public interface AccompanyDoccumentDAO {
	
	public List<AccompanyingDocument> getAccompanyingDocument(int appl_id);
}
