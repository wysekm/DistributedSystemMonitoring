package pl.edu.agh.dsm.common.annotations;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Component
@Retention(RetentionPolicy.RUNTIME)
// @Transactional(propagation = Propagation.REQUIRED)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface UseCase {
	String value() default "";
}