package pl.edu.agh.dsm.monitor.internalApi;

import java.util.UUID;

//klasa okreslajaca czy mozna usunac pomiar
public interface MeasurementPermisionResolver {
	boolean canRemove(UUID uuid);
}
