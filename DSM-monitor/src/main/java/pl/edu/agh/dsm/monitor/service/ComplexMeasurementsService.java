package pl.edu.agh.dsm.monitor.service;

import java.util.UUID;

import pl.edu.agh.dsm.common.ActionPossibility;
import pl.edu.agh.dsm.common.annotations.UseCase;
import pl.edu.agh.dsm.monitor.dto.ComplexMeasurementDto;

public interface ComplexMeasurementsService {

	//@Secured("{ROLE_USER}")
	@UseCase("UC_PF_MT4D")
	public void create(ComplexMeasurementDto measurement);
	
	//@Secured("{ROLE_USER}")
	@UseCase("UC_PF_MT4F")
	public void delete(UUID uuid);
	public ActionPossibility canDelete(UUID uuid);
	
	@UseCase("UC_PF_MT4B")
	public ComplexMeasurementDto getDetails(UUID uuid);
	
	public boolean isComplex(UUID uuid);
}
