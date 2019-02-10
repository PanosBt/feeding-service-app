package gr.hua.dit.feeding_service_app.api.exceptions;

public class BadRequestException extends RuntimeException {
	
	public BadRequestException() {
		super();
	}
	
	public BadRequestException(String message, Throwable cause) {
		super(message, cause);
	}

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(Throwable cause) {
		super(cause);
	}

}
