package com.it.bo.server.views;


import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public abstract class BaseView extends Panel implements View{
	

	private static final long serialVersionUID = -4668546500877738112L;
	
	protected String title;
	private VerticalLayout viewRoot;
	protected VerticalLayout headerComponent;
	
	protected boolean isShutdownMode;

	private VerticalLayout shutdownLabelLayout;

	private Label shutdownLabel;
	
	protected BaseView(String title) {
		this.title = title;
		setContent(buildViewContent());
	}

	protected abstract Component buildViewBody();
	
	private Component buildViewContent() {
		setSizeFull();
		setImmediate(true);
		VerticalLayout baseContainer = new VerticalLayout();
		
		shutdownLabelLayout = buildShutdownLabel();
		shutdownLabelLayout.setVisible(isShutdownMode);
		
		
		addStyleName(ValoTheme.PANEL_BORDERLESS);
		setId("BaseView");

		viewRoot = new VerticalLayout();
		viewRoot.addStyleName("dashboard-view");
		
		Component viewHeader = buildHeader();
		Component viewBody = buildViewBody();
		
		viewRoot.addComponent(viewHeader);
		viewRoot.addComponent(viewBody);
		
		viewRoot.setExpandRatio(viewBody, 3);

		
		baseContainer.addComponent(shutdownLabelLayout);
		baseContainer.addComponent(viewRoot);
		
		baseContainer.setExpandRatio(viewRoot, 1);
		
		return baseContainer;
	}
	
	private VerticalLayout buildShutdownLabel() {
		shutdownLabelLayout = new VerticalLayout();
		shutdownLabelLayout.setImmediate(true);
		shutdownLabelLayout.addStyleName("shutdown-header-color");
		shutdownLabelLayout.setHeight(100, Unit.PIXELS);
		
		shutdownLabel = new Label();
		updateShutdownLabelCaption();
		shutdownLabel.setImmediate(true);
		shutdownLabel.setStyleName("shutdown-label-color");
		shutdownLabel.setSizeUndefined();
		shutdownLabelLayout.addComponent(shutdownLabel);
		
		shutdownLabelLayout.setComponentAlignment(shutdownLabel, Alignment.MIDDLE_CENTER);
		
		return shutdownLabelLayout;
	}

	private void updateShutdownLabelCaption() {
		shutdownLabel.setCaption("Server is going to shut down.");
	}

	protected VerticalLayout getRoot() {
		return viewRoot;
	}

	private Component buildHeader() {
		headerComponent = new VerticalLayout();
		headerComponent.addStyleName("viewheader");
		headerComponent.setSpacing(true);

		Label titleLabel = new Label(title);
		titleLabel.setSizeUndefined();
		titleLabel.addStyleName(ValoTheme.LABEL_H1);
		titleLabel.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		headerComponent.addComponent(titleLabel);
		return headerComponent;
	}
	
	public void setShutdownMode(boolean isShutdownMode) {
		this.isShutdownMode = isShutdownMode;
		shutdownLabelLayout.setVisible(isShutdownMode);
	}
	
}
