package pl.edu.agh.dsm.common;


import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class ActionPossibilityImpl implements ActionPossibility {

    boolean possible;
    String reason;

    public static ActionPossibility forTrue()
    {
        return new ActionPossibilityImpl(true,"");
    }

    public static ActionPossibility forFalse(String reason)
    {
        Preconditions.checkArgument(Strings.isNullOrEmpty(reason));
        return new ActionPossibilityImpl(false, reason);
    }

    public static ActionPossibility makeDecision(boolean possible, String optionalReason)
    {
        return possible ? forTrue() : forFalse(optionalReason);
    }


    private ActionPossibilityImpl(boolean possible, String reason) {
        this.possible = possible;
        this.reason = reason;
    }

    private ActionPossibilityImpl() {
    }

    public String getReason() {
        return reason;
    }

    public boolean isPossible() {
        return possible;
    }
}