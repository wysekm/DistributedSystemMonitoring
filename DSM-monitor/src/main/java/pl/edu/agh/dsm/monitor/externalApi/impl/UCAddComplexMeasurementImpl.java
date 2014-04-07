package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.dto.Measurement;
import pl.edu.agh.dsm.monitor.externalApi.UCAddComplexMeasurement;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.measurement.MeasurementRepository;

import java.util.UUID;

@UseCase("UC_PF_MT4D")
public class UCAddComplexMeasurementImpl implements UCAddComplexMeasurement {

    ComplexMeasurementFactory measurementRepository;
    AutorizationContext autorizationContext;
    CatalogueProxy catalogueProxy;


    @Autowired
    public UCAddComplexMeasurementImpl(AutorizationContext autorizationContext, CatalogueProxy catalogueProxy, ComplexMeasurementFactory measurementRepository) {
        this.autorizationContext = autorizationContext;
        this.catalogueProxy = catalogueProxy;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void create(ComplexMeasurement complexMeasurement) {
        ApplicationUser applicationUser = autorizationContext.getActiveUser();
        Measurement measurement = measurementRepository.create(complexMeasurement,applicationUser);
        catalogueProxy.addMeasurement(measurement);
    }
}
