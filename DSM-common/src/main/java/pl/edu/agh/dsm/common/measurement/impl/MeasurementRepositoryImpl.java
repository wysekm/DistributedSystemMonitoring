package pl.edu.agh.dsm.common.measurement.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.common.measurement.MeasurementRepository;

import java.util.List;
import java.util.UUID;

public class MeasurementRepositoryImpl implements MeasurementRepository {

    static final Logger logger = LoggerFactory.getLogger(MeasurementRepositoryImpl.class);

    List<MeasurementDto> collector = Lists.newArrayList();

    @Override
    public List<MeasurementDto> findAll(Predicate<MeasurementDto> preconditions) {
        logger.debug("find all measurements with conditions {}" , preconditions);

        Iterable<MeasurementDto> iter = Lists.newArrayList();
        Iterables.addAll(collector, iter);

        return Lists.newArrayList(Iterables.filter(iter, preconditions));
    }

    @Override
    public MeasurementDto find(final UUID uuid) {
        logger.debug("find measurement with id {}" , uuid);

        Iterable<MeasurementDto> iter = Lists.newArrayList();
        Iterables.addAll(collector, iter);

        return Iterables.find(iter, new Predicate<MeasurementDto>() {
            @Override
            public boolean apply(MeasurementDto measurementDto) {
                return measurementDto.getId().equals(uuid);
            }
        });
    }

    @Override
    public void remove(UUID uuid) {
        logger.debug("remove measurement with id {}" , uuid);

        collector.remove(find(uuid));
    }

    @Override
    public void save(MeasurementDto measurementDto) {
        logger.debug("save measurement {}" , measurementDto);

        collector.add(measurementDto);
    }
}
