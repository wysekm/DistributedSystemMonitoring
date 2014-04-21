package pl.edu.agh.dsm.common;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }

    public static void check(ActionPossibility actionPossibility)
    {
        if(!actionPossibility.isPossible()) throw new IllegalArgumentException(actionPossibility.getReason());
    }
}
