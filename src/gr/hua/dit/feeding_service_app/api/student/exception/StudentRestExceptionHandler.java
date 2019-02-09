package gr.hua.dit.feeding_service_app.api.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gr.hua.dit.feeding_service_app.api.student.response_helper.StudentErrorResponse;

@ControllerAdvice
public class StudentRestExceptionHandler {
	
	StudentErrorResponse errorResponse = new StudentErrorResponse();
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException ex) {
		ex.printStackTrace();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<StudentErrorResponse> handleException(Exception ex) {
		ex.printStackTrace();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		//Don't show message if unpredicted Exception is thrown
//		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
