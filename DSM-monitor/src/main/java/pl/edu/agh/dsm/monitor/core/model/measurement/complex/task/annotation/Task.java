package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by Tom on 2014-05-29.
 */

@Inherited
@Component
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE, proxyMode = ScopedProxyMode.INTERFACES)
public @interface Task {
	String name() default "";
	String code() default "";
	Parameter[] parameters() default {};
}
