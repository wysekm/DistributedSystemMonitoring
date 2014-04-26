package pl.edu.agh.dsm.common.measurement.impl;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.dto.SystemResourceDto;
import pl.edu.agh.dsm.common.measurement.SystemResourceRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SystemResourceRepositoryImpl implements SystemResourceRepository {

    static final Logger logger = LoggerFactory.getLogger(SystemResourceRepositoryImpl.class);

    @Autowired
    MeasurementRepositoryImpl measurementRepo;

    @Override
    public List<SystemResourceDto> findAll() {
        logger.debug("find all resources");

        List<SystemResourceDto> listOfResources = new ArrayList<>();
        Predicate<MeasurementDto> predicate = Predicates.alwaysTrue();
        List<MeasurementDto> listOfMeasurements = measurementRepo.findAll(predicate);

        Set<String> resources = new HashSet<>();
        for (MeasurementDto dto : listOfMeasurements) {
            resources.add(dto.getResource());
        }

        for (String name : resources) {
            SystemResourceDto resourceDto = new SystemResourceDto(name);
            listOfResources.add(resourceDto);
        }

        return listOfResources;
    }
}
