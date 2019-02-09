package gr.hua.dit.feeding_service_app.services;

import java.util.List;
import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;

public interface AccompanyingDocumentService {
	//returns all the documents associated with the Application
	public List<AccompanyingDocument> getAccompanyingDocuments(int appl_id);

}
