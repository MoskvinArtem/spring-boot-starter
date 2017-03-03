package com.it.bo.server.framework;

import com.vaadin.navigator.View;

public interface EventAwareView extends View {
	void setEventsHandler(ViewEventHandler eventHandler);
}
