package pl.edu.agh.dsm.common.repository;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import pl.edu.agh.dsm.common.dto.MeasurementDto;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Component
public class MeasurementRepositoryImpl implements MeasurementRepository {

	static final Logger logger = LoggerFactory
			.getLogger(MeasurementRepositoryImpl.class);
	static List<MeasurementDto> repo = Lists.newArrayList();

	@Override
	public List<MeasurementDto> findAll(Predicate<MeasurementDto> preconditions) {
		logger.debug("find all measurements with conditions {}", preconditions);

		return Lists.newArrayList(Iterables.filter(repo, preconditions));
	}

	@Override
	public MeasurementDto find(final UUID uuid) {
		logger.debug("find measurement with id {}", uuid);
		
		try {
			return Iterables.find(repo, new Predicate<MeasurementDto>() {
				@Override
				public boolean apply(MeasurementDto measurementDto) {
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
	public void save(MeasurementDto measurementDto) {
		logger.debug("save measurement {}", measurementDto);

		repo.add(measurementDto);
	}
}
