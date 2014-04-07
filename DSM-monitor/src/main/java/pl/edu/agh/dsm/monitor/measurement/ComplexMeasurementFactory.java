package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;

//validate adn create
public interface ComplexMeasurementFactory {
    MeasurementDto create(ComplexMeasurementDto complexMeasurementDto, ApplicationUser applicationUser);
}
