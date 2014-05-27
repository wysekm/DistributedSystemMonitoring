package pl.edu.agh.dsm.front.core.model.rest;

/**
 * Created by Tom on 2014-05-26.
 */
public class RestClientServiceException extends RuntimeException {

	public RestClientServiceException() {
	}

	public RestClientServiceException(String message) {
		super(message);
	}

	public RestClientServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public RestClientServiceException(Throwable cause) {
		super(cause);
	}
}
