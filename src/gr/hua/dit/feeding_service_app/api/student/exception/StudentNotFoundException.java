package gr.hua.dit.feeding_service_app.api.student.exception;

public class StudentNotFoundException extends RuntimeException {
	
	public StudentNotFoundException() {
		super();
	}
	
	public StudentNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public StudentNotFoundException(String message) {
		super(message);
	}

	public StudentNotFoundException(Throwable cause) {
		super(cause);
	}

}
