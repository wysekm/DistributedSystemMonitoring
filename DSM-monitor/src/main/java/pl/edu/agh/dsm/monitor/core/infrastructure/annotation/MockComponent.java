package pl.edu.agh.dsm.monitor.core.infrastructure.annotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Primary
@Retention(RetentionPolicy.RUNTIME)
// @Transactional(propagation = Propagation.REQUIRED)
@Target(ElementType.TYPE)
public @interface MockComponent {
	String value() default "";
}