package pl.edu.agh.dsm.monitor.core.infrastructure.repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.monitor.core.model.measurement.Measurement;
import pl.edu.agh.dsm.monitor.core.model.measurement.MeasurementRepository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

@Component
public class MeasurementRepositoryImpl implements MeasurementRepository {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementRepositoryImpl.class);
	static List<Measurement> repo = Lists.newArrayList();

	@Override
	public List<Measurement> findAll(Predicate<Measurement> preconditions) {
		logger.debug("find all measurements with conditions {}", preconditions);

		return Lists.newArrayList(Iterables.filter(repo, preconditions));
	}

	@Override
	public Measurement find(final UUID uuid) {
		logger.debug("find measurement with id {}", uuid);
		
		try {
			return Iterables.find(repo, new Predicate<Measurement>() {
				@Override
				public boolean apply(Measurement measurementDto) {
					return measurementDto.getId().equals(uuid);
				}
			});
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public void remove(UUID uuid) {
		logger.debug("remove measurement with id {}", uuid);

		repo.remove(find(uuid));
	}

	@Override
	public void save(Measurement measurementDto) {
		logger.debug("save measurement {}", measurementDto);

		repo.add(measurementDto);
	}
        
        public void removeAll(UUID uuid) {
		logger.debug(
				"remove all measurementData with measurement uuid {} from repo",
				uuid);
                repo.remove(uuid);
	}
}
