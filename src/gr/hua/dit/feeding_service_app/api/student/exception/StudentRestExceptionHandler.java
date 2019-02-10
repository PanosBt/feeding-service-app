package gr.hua.dit.feeding_service_app.api.student.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import gr.hua.dit.feeding_service_app.api.exceptions.response_helper.RestErrorResponse;

@ControllerAdvice
public class StudentRestExceptionHandler {
	
	RestErrorResponse errorResponse = new RestErrorResponse();


}
