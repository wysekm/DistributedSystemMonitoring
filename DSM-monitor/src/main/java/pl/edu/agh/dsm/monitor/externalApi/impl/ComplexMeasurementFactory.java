package pl.edu.agh.dsm.monitor.externalApi.impl;

import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.dto.Measurement;

//validate adn create
public interface ComplexMeasurementFactory {
    Measurement create(ComplexMeasurement complexMeasurement, ApplicationUser applicationUser);
}
