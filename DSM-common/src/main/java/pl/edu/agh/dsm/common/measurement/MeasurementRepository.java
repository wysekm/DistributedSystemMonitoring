package pl.edu.agh.dsm.common.measurement;

import com.google.common.base.Predicate;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.List;
import java.util.UUID;

//proste repozytorium zasobów. Przechowuje zaosoby w aplikacji
public interface MeasurementRepository {
    List<MeasurementDto> findAll(Predicate<MeasurementDto> preconditions);

    MeasurementDto find(UUID uuid);

    void remove(UUID uuid);
    void save(MeasurementDto measurementDto);
}
