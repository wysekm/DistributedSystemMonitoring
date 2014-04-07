package pl.edu.agh.dsm.monitor.externalApi;

import pl.edu.agh.dsm.common.DescribedBoolean;

import java.util.UUID;

public interface UCDeleteComplexMeasurement {
    public void delete(UUID uuid) ;
    public DescribedBoolean havePermission(UUID uuid);
}
