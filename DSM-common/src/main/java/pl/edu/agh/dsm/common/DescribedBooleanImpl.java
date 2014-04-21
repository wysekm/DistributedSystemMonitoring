package pl.edu.agh.dsm.common;


public class DescribedBooleanImpl implements DescribedBoolean {

    boolean value;
    String reason;

    public DescribedBooleanImpl(boolean value, String reason) {
        this.value = value;
        this.reason = reason;
    }

    public DescribedBooleanImpl() {
    }

    public String getReason() {
        return reason;
    }

    public boolean getValue() {
        return value;
    }
}