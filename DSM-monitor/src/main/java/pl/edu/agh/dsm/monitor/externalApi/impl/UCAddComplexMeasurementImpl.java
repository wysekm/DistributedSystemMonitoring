package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;
import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.externalApi.UCAddComplexMeasurement;
import pl.edu.agh.dsm.common.security.AutorizationContext;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.measurement.ComplexMeasurementTaskFactory;

@UseCase("UC_PF_MT4D")
public class UCAddComplexMeasurementImpl implements UCAddComplexMeasurement {

    MeasurementRepository measurementRepository;
    ComplexMeasurementTaskFactory complexMeasurementTaskFactory;
    AutorizationContext autorizationContext;
    CatalogueProxy catalogueProxy;

    @Autowired
    public UCAddComplexMeasurementImpl(AutorizationContext autorizationContext, CatalogueProxy catalogueProxy, ComplexMeasurementTaskFactory complexMeasurementTaskFactory, MeasurementRepository measurementRepository) {
        this.autorizationContext = autorizationContext;
        this.catalogueProxy = catalogueProxy;
        this.complexMeasurementTaskFactory = complexMeasurementTaskFactory;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void create(ComplexMeasurementDto complexMeasurementDto) {
        ApplicationUser applicationUser = autorizationContext.getActiveUser();
        MeasurementDto measurementDto = complexMeasurementTaskFactory.create(complexMeasurementDto,applicationUser);
        measurementRepository.save(measurementDto);
        catalogueProxy.addMeasurement(measurementDto);
    }
}
