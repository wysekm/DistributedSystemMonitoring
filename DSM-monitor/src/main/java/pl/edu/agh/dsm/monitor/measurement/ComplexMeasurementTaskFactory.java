package pl.edu.agh.dsm.monitor.measurement;

import pl.edu.agh.dsm.common.security.ApplicationUser;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDto;

/*
* Klsa wykonuje nastepujace zadania
* Tworzy obiekt klasy measurement ktory bedzie reprezentowal pomiar zlozony
* Tworzy i ruchamia nowe taski które co jakis czas będą dodawały nowe pomiary złożone
* */
public interface ComplexMeasurementTaskFactory {
    MeasurementDto create(ComplexMeasurementDto complexMeasurementDto, ApplicationUser applicationUser);
}
