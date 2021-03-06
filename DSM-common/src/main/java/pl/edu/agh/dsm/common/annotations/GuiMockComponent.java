package pl.edu.agh.dsm.common.annotations;

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
public @interface GuiMockComponent {
	String value() default "";
}