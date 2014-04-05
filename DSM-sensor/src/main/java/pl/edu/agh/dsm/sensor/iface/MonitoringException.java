package pl.edu.agh.dsm.sensor.iface;

public class MonitoringException extends Exception {

    private final boolean fatal;

    public MonitoringException(String message, Throwable reason) {
        this(message, reason, false);

    }

    public MonitoringException(String message, Throwable reason, boolean fatal) {
        super(message, reason);
        this.fatal = fatal;
    }

    public boolean isFatal() {
        return fatal;
    }
}
