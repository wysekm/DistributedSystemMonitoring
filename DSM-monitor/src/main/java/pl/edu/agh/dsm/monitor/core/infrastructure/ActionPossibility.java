package pl.edu.agh.dsm.monitor.core.infrastructure;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

public class ActionPossibility {

	boolean possible;
	String reason;

	public static ActionPossibility forTrue() {
		return new ActionPossibility(true, "");
	}

	public static ActionPossibility forFalse(String reason) {
		Preconditions.checkArgument(Strings.isNullOrEmpty(reason));
		return new ActionPossibility(false, reason);
	}

	public static ActionPossibility makeDecision(boolean possible,
			String optionalReason) {
		return possible ? forTrue() : forFalse(optionalReason);
	}

	private ActionPossibility(boolean possible, String reason) {
		this.possible = possible;
		this.reason = reason;
	}

	private ActionPossibility() {
	}

	public String getReason() {
		return reason;
	}

	public boolean isPossible() {
		return possible;
	}
}