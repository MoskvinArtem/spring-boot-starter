package com.it.bo.server.views;


import com.it.bo.server.controllers.MainViewController;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;

public class MainView extends HorizontalLayout {


	private static final long serialVersionUID = 8300982875513847469L;
	private ComponentContainer mainContent;
    
    public MainView(MainViewController eventHandler) {
   	
    	setSizeFull();
        addStyleName("mainview");
        
        AdminMenu adminMenu = new AdminMenu(eventHandler);

        addComponent(adminMenu);

        mainContent = new CssLayout();
        mainContent.addStyleName("view-content");
        mainContent.setSizeFull();
        addComponent(mainContent);
        setExpandRatio(mainContent, 1.0f);
        
        

    }
    

    
    public ComponentContainer getContainer() {
    	return mainContent;
    }
    

}