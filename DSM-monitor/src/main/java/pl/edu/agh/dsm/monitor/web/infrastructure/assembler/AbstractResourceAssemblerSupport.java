package pl.edu.agh.dsm.monitor.web.infrastructure.assembler;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import pl.edu.agh.dsm.monitor.core.model.user.ApplicationUser;

import java.util.List;

public abstract class AbstractResourceAssemblerSupport<T> {

	public Resources<Resource<T>> addLinks(List<T> filter, final ApplicationUser user) {

		List<Resource<T>> out = Lists.transform(filter,
				new Function<T, Resource<T>>() {
					@Override
					public Resource<T> apply(T measurement) {
						return addLinks(measurement, user);
					}
				});
		return new Resources<>(out);
	}

	public abstract Resource<T> addLinks(T measurement, ApplicationUser user);

	public Resources<Resource<T>> addLinks(List<T> filter) {
		return addLinks(filter, null);
	}

	public Resource<T> addLinks(T measurement) {
		return addLinks(measurement, null);
	}
}
