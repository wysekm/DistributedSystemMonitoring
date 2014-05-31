package pl.edu.agh.dsm.monitor.core.infrastructure;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.springframework.http.HttpStatus;

public class ActionPossibility {

	boolean possible;
	String reason;
	HttpStatus failureStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	public static ActionPossibility forTrue() {
		return new ActionPossibility(true, "");
	}

	public static ActionPossibility forFalse(String reason) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(reason));
		return new ActionPossibility(false, reason);
	}

	public static ActionPossibility forFalse(String reason, HttpStatus status) {
		Preconditions.checkArgument(!Strings.isNullOrEmpty(reason));
		return new ActionPossibility(false, reason, status);
	}

	public ActionPossibility(boolean possible, String reason) {
		this(possible, reason, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ActionPossibility(boolean possible, String failureReason, HttpStatus failureStatus) {
		this.possible = possible;
		this.reason = failureReason;
		this.failureStatus = failureStatus;
	}

	public String getReason() {
		return reason;
	}

	public boolean isPossible() {
		return possible;
	}

	public HttpStatus getFailureStatus() {
		return failureStatus;
	}

	@Override
	public String toString() {
		return "ActionPossibility{" +
				"possible=" + possible +
				", reason='" + reason + '\'' +
				", failureStatus=" + failureStatus +
				'}';
	}
}