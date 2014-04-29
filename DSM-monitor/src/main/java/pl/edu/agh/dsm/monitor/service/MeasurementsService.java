package pl.edu.agh.dsm.monitor.service;

import java.util.List;
import java.util.UUID;

import pl.edu.agh.dsm.common.annotations.UseCase;
import pl.edu.agh.dsm.common.dto.MeasurementDto;
import pl.edu.agh.dsm.monitor.dto.MeasurementDataDto;
import pl.edu.agh.dsm.monitor.dto.SimpleMeasurementDataDto;
import pl.edu.agh.dsm.monitor.repository.predicate.DataLimit;

public interface MeasurementsService {
	
	public List<MeasurementDto> getList(String metric, String resource);
	
	@UseCase("UC_PF_MT4B")
	public MeasurementDto getDetails(UUID uuid);
	
	@UseCase("UC_PF_MT4C")
	public List<MeasurementDataDto> getData(UUID uuid, DataLimit limit, int value);
	
	public void addNewData(SimpleMeasurementDataDto dto);
	
}
