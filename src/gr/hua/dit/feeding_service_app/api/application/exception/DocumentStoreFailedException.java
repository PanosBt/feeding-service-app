package gr.hua.dit.feeding_service_app.api.application.exception;

public class DocumentStoreFailedException extends RuntimeException {

	public DocumentStoreFailedException() {
		super();
	}

	public DocumentStoreFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocumentStoreFailedException(String message) {
		super(message);
	}

	public DocumentStoreFailedException(Throwable cause) {
		super(cause);
	}

}
