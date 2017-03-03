package com.it.bo.server.framework;


import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.it.bo.server.utils.NavigationUtils;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewProvider;

@Component
public class AdminViewProvider implements ViewProvider {
	
	
	private static final long serialVersionUID = -7565887568557638760L;
	
	
	protected static final Logger logger = LoggerFactory.getLogger(AdminViewProvider.class);
	
	
	@Inject
	private ControllersFacade controllersFacade;
	
	

	@Override
	public String getViewName(String viewAndParameters) {
		int paramsIndex = viewAndParameters.indexOf(NavigationUtils.PARAM_DELIMITER);
		if(paramsIndex == -1) {
			return viewAndParameters;
		}
         return viewAndParameters.substring(0, paramsIndex);
	}

	@Override
	public View getView(String viewName) {
		BaseController<?> resultController = controllersFacade.getController(viewName);
		if(resultController == null) {
			logger.warn("Cannot find controller for viewName {} and default view is not set");
			return null;
		}
		return resultController.getView();
	}

}

