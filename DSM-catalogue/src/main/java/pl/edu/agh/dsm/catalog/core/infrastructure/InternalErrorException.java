package pl.edu.agh.dsm.catalog.core.infrastructure;

import org.springframework.http.HttpStatus;

public class InternalErrorException extends RuntimeException {

	public static class ErrorMessageDto {
		String message;

		public ErrorMessageDto(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1125970827453109021L;

	private HttpStatus failureStatus;

	public InternalErrorException(String message) {
		super(message);
		failureStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	}

	public InternalErrorException(String message, HttpStatus failureStatus) {
		super(message);
		this.failureStatus = failureStatus;
	}

	public HttpStatus getFailureStatus() {
		return failureStatus;
	}

	public void setFailureStatus(HttpStatus failureStatus) {
		this.failureStatus = failureStatus;
	}

	public static void check(ActionPossibility actionPossibility) {
		if (!actionPossibility.isPossible()) {
			throw new InternalErrorException(actionPossibility.getReason(),
					actionPossibility.getFailureStatus());
		}
	}

	public ErrorMessageDto getErrorMessageDto() {
		return new ErrorMessageDto(getMessage());
	}
}
