package com.it.bo.server.controllers;


import org.springframework.stereotype.Component;

import com.it.bo.server.utils.NavigationUtils;
import com.it.bo.server.views.AdminMenuItem;
import com.it.bo.server.views.MainView;


@Component
public class MainViewController {
	

	
	private MainView view;

	public void initView() {
		view = new MainView(this);
	}
	
	public MainView getView() {
		return this.view;
	}


	public void navigateToView(AdminMenuItem menuItem) {
		NavigationUtils.navigateTo(menuItem.getViewName());
	}
}
