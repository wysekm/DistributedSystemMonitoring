package pl.edu.agh.dsm.monitor.measurement.impl;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.measurement.MeasurementDataRepository;

import java.util.*;


public class MeasurementDataRepositoryImpl implements MeasurementDataRepository {

    static final Logger logger = LoggerFactory.getLogger(MeasurementDataRepositoryImpl.class);
    static Map<UUID, List<MeasurementDataDto>> repo = new HashMap<>();

    @Override
    public List<MeasurementDataDto> find(UUID uuid, Predicate<MeasurementDataDto> preconditions) {
        logger.debug("find list of MeasurementDataDto with measurement uuid {} and data predicate {}", uuid, preconditions);

        List<MeasurementDataDto> measurementDataList = repo.get(uuid);
        Iterable<MeasurementDataDto> iter = Lists.newArrayList();
        Iterables.addAll(measurementDataList, iter);

        return Lists.newArrayList(Iterables.filter(iter, preconditions));
    }

    @Override
    public void add(UUID uuid, MeasurementDataDto measurementDataDto) {
        logger.debug("add new measurementData {} with measurement uuid {} to repo", measurementDataDto, uuid);

        List<MeasurementDataDto> measurementDataList = repo.get(uuid);

        if (measurementDataList == null) {
            measurementDataList = new ArrayList<>();
            measurementDataList.add(measurementDataDto);
            repo.put(uuid, measurementDataList);
        } else {
            measurementDataList.add(measurementDataDto);
        }
    }

    @Override
    public void remove(UUID uuid, MeasurementDataDto measurementDataDto) {
        logger.debug("remove measurementData {} with measurement uuid {} from repo", measurementDataDto, uuid);

        List<MeasurementDataDto> measurementDataList = repo.get(uuid);
        if (measurementDataList != null) {
            measurementDataList.remove(measurementDataDto);
        }
    }
}
