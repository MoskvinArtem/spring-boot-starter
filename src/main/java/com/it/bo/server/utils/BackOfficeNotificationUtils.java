package com.it.bo.server.utils;

import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;

public class BackOfficeNotificationUtils {

	public static void showSuccessButtomLineNotification(String caption) {
		Notification success = new Notification(caption);
		success.setDelayMsec(2000);
		success.setStyleName("bar success small");
		success.setPosition(Position.BOTTOM_CENTER);
		success.show(Page.getCurrent());
	}
	
	public static void showErrorNotification(String caption) {
		Notification error = new Notification(caption);
		error.setPosition(Position.BOTTOM_CENTER);
		error.setStyleName(ValoTheme.NOTIFICATION_ERROR);
		error.show(Page.getCurrent());
	}

}
