package com.it.bo.server.ui;


import java.util.Locale;

import javax.inject.Inject;

import com.it.bo.server.controllers.MainViewController;
import com.it.bo.server.framework.AdminViewProvider;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;


@Theme("dashboard")
@Title("Admin")
@SpringUI
@SuppressWarnings("serial")
public class AdminUI extends UI {
	
	@Inject
	private AdminViewProvider viewProvider;
	
	@Inject
	private MainViewController mainViewController;


    @Override
    protected void init(VaadinRequest request) {

    	setLocale(Locale.US);
        addStyleName(ValoTheme.UI_WITH_MENU);
        
        mainViewController.initView();
        
        Navigator navigator = new Navigator(this, mainViewController.getView().getContainer());
        navigator.addProvider(viewProvider);
        
        setNavigator(navigator);
        
        setContent(mainViewController.getView());

        
    }

}
