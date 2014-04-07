package pl.edu.agh.dsm.common;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
public class DescribedBooleanImpl implements DescribedBoolean {

    boolean value;
    String reason;

    public String getReason() {
        return reason;
    }

    public boolean getValue() {
        return value;
    }
}