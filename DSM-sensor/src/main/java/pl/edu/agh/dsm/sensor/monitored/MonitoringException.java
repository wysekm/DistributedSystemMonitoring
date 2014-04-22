package pl.edu.agh.dsm.sensor.monitored;

public class MonitoringException extends Exception {
    private boolean fatal;

    public MonitoringException(String message) {
        this(message, false);
    }

    public MonitoringException(String message, Throwable cause) {
        this(message, cause, false);
    }

    public MonitoringException(String message, boolean fatal) {
        super(message);
        this.fatal = fatal;
    }

    public MonitoringException(String message, Throwable cause, boolean fatal) {
        super(message, cause);
        this.fatal = fatal;
    }

    public boolean isFatal() {
        return fatal;
    }
}
