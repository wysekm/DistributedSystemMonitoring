package pl.edu.agh.dsm.monitor.measurement;

import java.util.UUID;

//klasa okreslajaca czy mozna usunac pomiar
public interface MeasurementPermisionResolver {
    boolean canRemove(UUID uuid);
}
