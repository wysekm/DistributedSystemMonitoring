package pl.edu.agh.dsm.monitor.measurement.impl;

import java.util.UUID;

import org.junit.Test;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

public class CatalogueProxyImplTest  {
    @Test public void testAdd() {
        CatalogueProxyImpl proxy = new CatalogueProxyImpl();
        MeasurementDto measurement = new MeasurementDto(
                UUID.fromString("a8bfb961-c123-4aac-a686-48a33de8cb11"),
                "Host", "memUsage", "mb", "http://localhost:8085");
        proxy.addMeasurement(measurement);
    }
    
    @Test public void testDelete() {
        CatalogueProxyImpl proxy = new CatalogueProxyImpl();
        MeasurementDto measurement = new MeasurementDto(
                UUID.fromString("a8bfb961-c123-4aac-a686-48a33de8cb11"),
                "Host", "memUsage", "mb", "http://localhost:8085");
        proxy.removeMeasurement(measurement.getId());
    }
}