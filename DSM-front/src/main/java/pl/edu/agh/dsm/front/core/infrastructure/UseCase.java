package pl.edu.agh.dsm.front.core.infrastructure;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
// @Transactional(propagation = Propagation.REQUIRED)
@Target(ElementType.TYPE)
public @interface UseCase {
	String value() default "";
}