package com.it.bo.server.utils;


import java.util.Map;

import com.vaadin.ui.UI;

public class NavigationUtils {
	
	public static final String PARAM_DELIMITER = "/";
	
	public static void navigateTo(String viewName) {
		navigateTo(viewName, null);
	}
	
	public static void navigateTo(String viewName, Map<String, String> params) {
		UI.getCurrent().getNavigator().navigateTo(viewName + buildNavigationParams(params));
	}


	public static String getNavigationParam(String targetParamName, String parameters) {
		String[] urlParams = parameters.split(PARAM_DELIMITER);
		for (int i=0; i < urlParams.length; i++) {
			if(targetParamName.equalsIgnoreCase(urlParams[i]) && (i+1) < urlParams.length) {
				return urlParams[i+1];
			}
		}
		return null;
	}
	
	
	
	private static String buildNavigationParams(Map<String, String> params) {
		String navigationParams = PARAM_DELIMITER;
		if (params == null) {
			return navigationParams;
		}

		for (String paramName : params.keySet()) {
			navigationParams += paramName + PARAM_DELIMITER + params.get(paramName) + PARAM_DELIMITER;
		}
		return navigationParams;
	}

}
