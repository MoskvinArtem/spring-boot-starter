package com.it.bo.server.views;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;

public enum AdminMenuItem {
	
	
	DASHBOARD(UserManagementView.NAME, FontAwesome.HOME),
	;
    

    private final String viewName;
    private final Resource icon;

    AdminMenuItem(String viewName, Resource icon) {
        this.viewName = viewName;
        this.icon = icon;
    }

    
    public String getViewName() {
        return viewName;
    }


    public Resource getIcon() {
        return icon;
    }
}
