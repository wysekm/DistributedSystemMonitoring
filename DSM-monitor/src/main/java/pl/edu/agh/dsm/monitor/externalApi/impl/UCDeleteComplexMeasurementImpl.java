package pl.edu.agh.dsm.monitor.externalApi.impl;

import org.springframework.beans.factory.annotation.Autowired;

import pl.edu.agh.dsm.common.ActionPossibility;
import pl.edu.agh.dsm.common.ActionPossibilityImpl;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;
import pl.edu.agh.dsm.monitor.externalApi.UCDeleteComplexMeasurement;
import pl.edu.agh.dsm.monitor.annotations.UseCase;
import pl.edu.agh.dsm.monitor.measurement.CatalogueProxy;
import pl.edu.agh.dsm.monitor.measurement.MeasurementPermisionResolver;

import java.util.UUID;

import static pl.edu.agh.dsm.common.InternalErrorException.*;

@UseCase("UC_PF_MT4F")
public class UCDeleteComplexMeasurementImpl implements UCDeleteComplexMeasurement {

    MeasurementRepository measurementRepository;
    CatalogueProxy catalogueProxy;
    MeasurementPermisionResolver measurementPermisionResolver;


    @Autowired
    public UCDeleteComplexMeasurementImpl(CatalogueProxy catalogueProxy, MeasurementPermisionResolver measurementPermisionResolver, MeasurementRepository measurementRepository) {
        this.catalogueProxy = catalogueProxy;
        this.measurementPermisionResolver = measurementPermisionResolver;
        this.measurementRepository = measurementRepository;
    }

    @Override
    public void delete(UUID uuid) {
        check(havePermission(uuid));
        measurementRepository.remove(uuid);
        catalogueProxy.removeMeasurement(uuid);

    }

    @Override
    public ActionPossibility havePermission(UUID uuid) {
        boolean havePerm = measurementPermisionResolver.canRemove(uuid);
        return ActionPossibilityImpl.makeDecision(havePerm, "Podany uzytkownik nie moze usunac tego pomiaru");
    }
}
