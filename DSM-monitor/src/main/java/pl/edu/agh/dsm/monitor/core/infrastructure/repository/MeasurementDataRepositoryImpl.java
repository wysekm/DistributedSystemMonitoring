package pl.edu.agh.dsm.monitor.core.infrastructure.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementDataRepository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import static pl.edu.agh.dsm.monitor.core.infrastructure.repository.MeasurementRepositoryImpl.logger;

@Component
public class MeasurementDataRepositoryImpl implements MeasurementDataRepository {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementDataRepositoryImpl.class);
	static Map<UUID, List<MeasurementData>> repo = new HashMap<>();
	
	@Value("${data.storing.limit}")
	int maxDataLimit;

	@Override
	public List<MeasurementData> find(UUID uuid,
			Predicate<MeasurementData> preconditions) {
		logger.debug(
				"find list of MeasurementDataDto with measurement uuid {} and data predicate {}",
				uuid, preconditions);

		List<MeasurementData> measurementDataList = repo.get(uuid);
		return Lists.newArrayList(Iterables.filter(measurementDataList,
				preconditions));
	}

	@Override
	public void add(UUID uuid, MeasurementData measurementDataDto) {
		logger.debug(
				"add new measurementData {} with measurement uuid {} to repo",
				measurementDataDto, uuid);

		List<MeasurementData> measurementDataList = repo.get(uuid);

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
	public void remove(UUID uuid, MeasurementData measurementDataDto) {
		logger.debug(
				"remove measurementData {} with measurement uuid {} from repo",
				measurementDataDto, uuid);

		List<MeasurementData> measurementDataList = repo.get(uuid);
		if (measurementDataList != null) {
			measurementDataList.remove(measurementDataDto);
		}
	}
        
        public void removeAll(UUID uuid) {
		logger.debug(
				"remove all measurementData with measurement uuid {} from repo",
				uuid);
                repo.remove(uuid);
	}
}
