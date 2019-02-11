package gr.hua.dit.feeding_service_app.api.application.controller;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gr.hua.dit.feeding_service_app.api.application.exception.ApplicationNotFoundException;
import gr.hua.dit.feeding_service_app.api.application.exception.DocumentStoreFailedException;
import gr.hua.dit.feeding_service_app.api.application.request_helper.ApplicationSubmitRequest;
import gr.hua.dit.feeding_service_app.api.application.response_helper.AccDocSubmitResponse;
import gr.hua.dit.feeding_service_app.api.application.response_helper.ApplicationSubmitResponse;
import gr.hua.dit.feeding_service_app.api.exceptions.BadRequestException;
import gr.hua.dit.feeding_service_app.api.student.exception.StudentNotFoundException;
import gr.hua.dit.feeding_service_app.entities.AccompanyingDocument;
import gr.hua.dit.feeding_service_app.entities.Application;
import gr.hua.dit.feeding_service_app.entities.Student;
import gr.hua.dit.feeding_service_app.services.AccompanyingDocumentService;
import gr.hua.dit.feeding_service_app.services.ApplicationService;
import gr.hua.dit.feeding_service_app.services.FileService;
import gr.hua.dit.feeding_service_app.services.StudentService;

@RestController
@RequestMapping("/api/application")
public class ApplicationRestController {

	@Autowired
	ApplicationService applicationService;

	@Autowired
	StudentService studentService;
	
	@Autowired
	FileService fileService;
	
	@Autowired
	AccompanyingDocumentService accompanyingDocumentService; 

	@PostMapping
	public ResponseEntity<String> submitApplication(@RequestBody ApplicationSubmitRequest applicationReq) {

		//TODO Add file support
		//TODO Add validation (not null etc) on fields
		//TODO Consider the above on rest controllers...
		Student student;
		if ((student = studentService.getStudent(applicationReq.getStudent_id())) == null)
			throw new StudentNotFoundException("student with id " + applicationReq.getStudent_id() + " not found");

		Application application = new Application(
				student, applicationReq.getFamilyIncome(),
				applicationReq.getNum_siblings(), applicationReq.getOrigin_city(),
				applicationReq.getMother_employeed(), applicationReq.getFather_employeed(),
				new Date());
		
		ApplicationSubmitResponse response = new ApplicationSubmitResponse(applicationService.save(application));
						
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(parseResponseToJSON(response));
	}
	
	@PostMapping(value = "/{id}/documents/{doc_type}")
	public ResponseEntity<String> uploadDocument(@RequestParam("document") MultipartFile doc,
			@PathVariable("id") Integer id, @PathVariable("doc_type") String doc_type) throws HttpMediaTypeNotSupportedException {
		
		if(id == null || doc_type == null)
			throw new BadRequestException();
		
		// Accept only pdf files
		if (!doc.getContentType().equals(MediaType.APPLICATION_PDF_VALUE))
			throw new HttpMediaTypeNotSupportedException(
					MediaType.parseMediaType(doc.getContentType()),
					Arrays.asList(MediaType.APPLICATION_PDF));
		
		Application application;
		
		if ((application = applicationService.getApplication(id)) == null)
			throw new ApplicationNotFoundException("application with id " + id + " not found");
		
		// save the document to the file system and get filepath
		String filePath = fileService.storeAccDoc(id, doc_type, doc);
		
		// if save failed filePath is null
		if (filePath == null)
			throw new DocumentStoreFailedException("document store failed.");
				
		AccompanyingDocument accDoc = new AccompanyingDocument(application, doc_type, filePath);
		
		// save the new AccompanyingDocument on DB and fetch new doc_id in response
		Integer doc_id = accompanyingDocumentService.save(accDoc);
		AccDocSubmitResponse response = new AccDocSubmitResponse(doc_id);
		
		return ResponseEntity.status(HttpStatus.CREATED)
				.contentType(MediaType.APPLICATION_JSON)
				.body(parseResponseToJSON(response));

	}
	
	private String parseResponseToJSON(Object response) {

		ObjectMapper mapper = new ObjectMapper();

		String jsonResponse = null;

		try {
			jsonResponse = mapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		return jsonResponse;
	}
}










