package pl.edu.agh.dsm.monitor.web.infrastructure.assembler;

import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Component;
import pl.edu.agh.dsm.monitor.core.model.measurement.complex.ComplexType;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

@Component
public class ComplexTypesAssemblerSupport extends
		AbstractResourceAssemblerSupport<ComplexType> {

	@Override
	public Resource<ComplexType> addLinks(ComplexType dto, ApplicationUser user) {
		return new Resource<>(dto);
	}
}
