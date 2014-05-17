package pl.edu.agh.dsm.monitor.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDataDto;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component
public class MeasurementDataRepositoryImpl implements MeasurementDataRepository {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementDataRepositoryImpl.class);
	static Map<UUID, List<MeasurementDataDto>> repo = new HashMap<>();
	
	@Value("${data.storing.limit}")
	int maxDataLimit;

	@Override
	public List<MeasurementDataDto> find(UUID uuid,
			Predicate<MeasurementDataDto> preconditions) {
		logger.debug(
				"find list of MeasurementDataDto with measurement uuid {} and data predicate {}",
				uuid, preconditions);

		List<MeasurementDataDto> measurementDataList = repo.get(uuid);
		return Lists.newArrayList(Iterables.filter(measurementDataList,
				preconditions));
	}

	@Override
	public void add(UUID uuid, MeasurementDataDto measurementDataDto) {
		logger.debug(
				"add new measurementData {} with measurement uuid {} to repo",
				measurementDataDto, uuid);

		List<MeasurementDataDto> measurementDataList = repo.get(uuid);

		if (measurementDataList == null) {
			measurementDataList = new ArrayList<>();
			measurementDataList.add(measurementDataDto);
			repo.put(uuid, measurementDataList);
		} else {
			int listSize = measurementDataList.size();
			if (listSize >= maxDataLimit) {
				measurementDataList.remove(listSize - 1);
			}
			measurementDataList.add(0, measurementDataDto);
		}
	}

	@Override
	public void remove(UUID uuid, MeasurementDataDto measurementDataDto) {
		logger.debug(
				"remove measurementData {} with measurement uuid {} from repo",
				measurementDataDto, uuid);

		List<MeasurementDataDto> measurementDataList = repo.get(uuid);
		if (measurementDataList != null) {
			measurementDataList.remove(measurementDataDto);
		}
	}
}
