package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.common.ActionPossibility;

import java.util.UUID;

public interface UCDeleteComplexMeasurement {
    public void delete(UUID uuid) ;
    public ActionPossibility havePermission(UUID uuid);
}
