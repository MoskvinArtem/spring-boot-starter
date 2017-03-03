package com.it.bo.server.components;


import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public abstract class BaseWindow extends Window {

	private static final long serialVersionUID = 8055608867305537318L;
	protected VerticalLayout rootContent;
	protected String closeCaption;
	protected String okButtonCaption;
	
	protected BaseWindow(String caption) {
		this(caption, "Close");
	}
	
	protected BaseWindow(String caption, String closeCaption, String okButtonCaption) {
		this(caption, closeCaption);
		this.okButtonCaption = okButtonCaption;
	}

	protected BaseWindow(String caption, String closeCaption) {

		this.closeCaption = closeCaption;
		
		setCaption(caption);

		setModal(true);

		setResizable(false);
		setClosable(false);
		setWidth(50, Unit.PERCENTAGE);

		rootContent = new VerticalLayout();
		rootContent.setMargin(new MarginInfo(true, false, false, false));
		setContent(rootContent);
		
	}

	protected abstract Component getBody();
	protected abstract void onSubmitButtonClick(ClickEvent clickEvent);
	
	protected void init() {
		Component body = getBody();
		rootContent.addComponent(body);
		rootContent.addComponent(getFooter());
	}



	protected Component getFooter() {
		HorizontalLayout footer = new HorizontalLayout();
		footer.addStyleName(ValoTheme.WINDOW_BOTTOM_TOOLBAR);
		footer.setWidth(100.0f, Unit.PERCENTAGE);
		
		HorizontalLayout buttonWrapper = new HorizontalLayout();
		buttonWrapper.setSpacing(true);
		
		if(okButtonCaption != null) {
			Button ok = new Button(okButtonCaption, this::onSubmitButtonClick);
			ok.addStyleName(ValoTheme.BUTTON_PRIMARY);
			buttonWrapper.addComponent(ok);
		}

		Button close = new Button(closeCaption, event -> close());
		close.addStyleName(ValoTheme.BUTTON_PRIMARY);
		
		buttonWrapper.addComponent(close);
		
		footer.addComponent(buttonWrapper);
		footer.setComponentAlignment(buttonWrapper, Alignment.TOP_RIGHT);
		
		return footer;
	}

}
