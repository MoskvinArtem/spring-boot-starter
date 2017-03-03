package com.it.bo.server.framework;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Controller to View linkage implementation
 * @author Evgenym
 *
 */
@Component
public class ControllersFacade {
	
	/**
	 * Package to scan controllers
	 */
	private static final String PACKAGE_TO_SCAN = "com.it.bo.server.controllers";

	protected static final Logger logger = LoggerFactory.getLogger(ControllersFacade.class);
	
	protected final static String DEFAULT_VIEW_KEY = "_default_view_";
	
	Map<String, Class<?>> controllerClasses = new HashMap<>();
	
	
	@Autowired
	private ApplicationContext appCtx;
	
	@PostConstruct
	public void init() {
		Reflections reflections = new Reflections(PACKAGE_TO_SCAN);
		Set<Class<?>> classes = reflections.getTypesAnnotatedWith(ControllingView.class);
		initControllersMap(classes);
	}

	/**
	 * Get and save all controllers
	 * @param classes all classes annotated with {@link com.it.bo.server.framework.ControllingView}
	 */
	private void initControllersMap(Set<Class<?>> classes) {
		for (Class<?> clazz : classes) {
			ControllingView targetView = clazz.getAnnotation(ControllingView.class);
			String viewName = targetView.value();
			if(targetView.defaultView()) {
				controllerClasses.put(ControllersFacade.DEFAULT_VIEW_KEY, clazz);
			}else{
				controllerClasses.put(viewName, clazz);
			}
		}
	}
	
	public BaseController<?> getController(String viewName) {
		Class<?> beanClass = controllerClasses.get(viewName);
		if(beanClass == null) {
			beanClass = controllerClasses.get(DEFAULT_VIEW_KEY);
		}
		BaseController<?> bean = (BaseController<?>) appCtx.getBean(beanClass);
		return bean;
	}

}
