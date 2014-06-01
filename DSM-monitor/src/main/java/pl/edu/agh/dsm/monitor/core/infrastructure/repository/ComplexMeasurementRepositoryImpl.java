package pl.edu.agh.dsm.monitor.core.infrastructure.repository;


import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexMeasurementsRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import pl.edu.agh.dsm.monitor.core.model.measurement.data.MeasurementData;


/**
 * Created by Tom on 2014-05-29.
 */
@Component
public class ComplexMeasurementRepositoryImpl implements ComplexMeasurementsRepository {
    
	static final Logger logger = LoggerFactory
			.getLogger(MeasurementRepositoryImpl.class);
	static List<ComplexMeasurement> repo = Lists.newArrayList();

	@Override
	public List<ComplexMeasurement> find(Predicate<ComplexMeasurement> preconditions) {
		logger.trace(
				"find list of ComplexMeasurement with predicate {}",
				preconditions);

		return Lists.newArrayList(Iterables.filter(repo,
				preconditions));
	}

	@Override
	public ComplexMeasurement find(final UUID uuid) {
		logger.trace("find complex measurement with id {}", uuid);
		
		try {
			return Iterables.find(repo, new Predicate<ComplexMeasurement>() {
				@Override
				public boolean apply(ComplexMeasurement measurementDto) {
					return measurementDto.getId().equals(uuid);
				}
			});
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public void remove(UUID uuid) {
		logger.debug("remove complex measurement with id {}", uuid);
		repo.remove(find(uuid));
	}

	@Override
	public void save(ComplexMeasurement measurement) {		
		logger.debug("save complex measurement {}", measurement);
		repo.add(measurement);

	}
}
