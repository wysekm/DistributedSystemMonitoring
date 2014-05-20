package pl.edu.agh.dsm.monitor.web.infrastructure;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import java.util.List;

public abstract class AbstractResourceAssemblerSupport<T> {
	public Resources<Resource<T>> addLinks(List<T> filter) {

		List<Resource<T>> out = Lists.transform(filter,
				new Function<T, Resource<T>>() {
					@Override
					public Resource<T> apply(T measurement) {
						return addLinks(measurement);
					}
				});
		return new Resources<>(out);
	}

	public abstract Resource<T> addLinks(T measurement);
}
