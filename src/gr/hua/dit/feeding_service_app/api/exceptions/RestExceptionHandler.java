package gr.hua.dit.feeding_service_app.api.exceptions;

import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import gr.hua.dit.feeding_service_app.api.application.exception.ApplicationNotFoundException;
import gr.hua.dit.feeding_service_app.api.application.exception.DocumentStoreFailedException;
import gr.hua.dit.feeding_service_app.api.exceptions.response_helper.RestErrorResponse;
import gr.hua.dit.feeding_service_app.api.student.exception.StudentNotFoundException;

@ControllerAdvice(basePackages = "gr.hua.dit.feeding_service_app.api")
public class RestExceptionHandler {

	RestErrorResponse errorResponse = new RestErrorResponse();

	@ExceptionHandler({ BadRequestException.class, MissingServletRequestParameterException.class,
			MethodArgumentTypeMismatchException.class, HttpMessageConversionException.class,
			MissingServletRequestPartException.class, MaxUploadSizeExceededException.class,
			SizeLimitExceededException.class})
	public ResponseEntity<RestErrorResponse> handleBadRequestException(Exception ex) {
		ex.printStackTrace();

		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({StudentNotFoundException.class, ApplicationNotFoundException.class})
	public ResponseEntity<RestErrorResponse> handleStudentNotFoundException(Exception ex) {
		ex.printStackTrace();
		errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMediaTypeNotSupportedException.class)
	public ResponseEntity<RestErrorResponse> handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException ex) {
		ex.printStackTrace();
		errorResponse.setStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
	} 
	
	@ExceptionHandler(DocumentStoreFailedException.class)
	public ResponseEntity<RestErrorResponse> handleDocumentStoreFailedException(DocumentStoreFailedException ex) {
		ex.printStackTrace();
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setMessage(ex.getMessage());
		errorResponse.setTimeStamp(System.currentTimeMillis());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// Handler for unpredicted exceptions
	@ExceptionHandler(Exception.class)
	public ResponseEntity<RestErrorResponse> handleException(Exception ex) {
		ex.printStackTrace();

		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		// Don't show message if unpredicted Exception is thrown
		errorResponse.setMessage("Internal Server Error. Your request could not be processed");
		errorResponse.setTimeStamp(System.currentTimeMillis());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
