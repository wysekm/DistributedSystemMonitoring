package pl.edu.agh.dsm.common;

public class InternalErrorException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InternalErrorException(Throwable cause) {
        super(cause);
    }

    public InternalErrorException(String message) {
		super(message);
	}

	public static void check(ActionPossibility actionPossibility) {
		if (!actionPossibility.isPossible())
			throw new IllegalArgumentException(actionPossibility.getReason());
	}
}
