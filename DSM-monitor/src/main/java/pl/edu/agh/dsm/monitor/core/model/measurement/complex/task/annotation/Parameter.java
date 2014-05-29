package pl.edu.agh.dsm.monitor.core.model.measurement.complex.task.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Tom on 2014-05-29.
 */
@Target(value = ElementType.ANNOTATION_TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Parameter {
	String name();
	String code();
	boolean required() default true;
}
