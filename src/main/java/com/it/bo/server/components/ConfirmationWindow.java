package com.it.bo.server.components;


import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.*;

import java.util.function.Consumer;

public class ConfirmationWindow<T> extends BaseWindow {

	private static final long serialVersionUID = -1390442858794028410L;

	private String confirmationCaption;
	private Consumer<T> confirmHandler;
	private T data;

	public ConfirmationWindow(String confirmationCaption, Consumer<T> confirmHandler, T data) {
		super("Please confirm your choice", "Cancel", "Confirm");
		this.confirmationCaption = confirmationCaption;
		this.confirmHandler = confirmHandler;
		this.data = data;
		
		init();

	}

	@Override
	protected Component getBody() {
		Label confirmText = new Label(confirmationCaption);
		
		Panel contentPanel = new Panel();
		
		VerticalLayout labelLayout = new VerticalLayout();
		labelLayout.setMargin(true);
		labelLayout.addComponent(confirmText);
		
		contentPanel.setContent(labelLayout);
		return contentPanel;
	}

	@Override
	protected void onSubmitButtonClick(ClickEvent clickEvent) {
		confirmHandler.accept(data);
		close();
	}

	public static <T> void open(String confirmationCaption, Consumer<T> confirmHandler, T data) {

		ConfirmationWindow<T> window = new ConfirmationWindow<>(confirmationCaption, confirmHandler, data);
		UI.getCurrent().addWindow(window);
		window.focus();
	}

}
