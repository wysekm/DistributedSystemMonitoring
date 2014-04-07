package pl.edu.agh.dsm.common;

public class InternalErrorException extends RuntimeException {

    public InternalErrorException(String message) {
        super(message);
    }

    public static void check(DescribedBoolean describedBoolean)
    {
        if(!describedBoolean.getValue()) throw new IllegalArgumentException(describedBoolean.getReason());
    }
}
