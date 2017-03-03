package com.it.bo.server.framework;

/**
 * Annotation for controllers in order to link View to controller
 */
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

import com.vaadin.spring.annotation.UIScope;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@UIScope
public @interface ControllingView {

    String value();

    boolean defaultView() default false;

}
