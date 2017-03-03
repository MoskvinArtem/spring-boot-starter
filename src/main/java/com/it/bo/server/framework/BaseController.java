package com.it.bo.server.framework;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.it.bo.server.views.BaseView;
import com.vaadin.navigator.View;

@Component
public abstract class BaseController<T extends BaseView> implements Serializable{
	
	private static final long serialVersionUID = -8040074573306332227L;
	
	protected T view;
	protected String name;
	
	
	public abstract void initView();
	
	public String getName() {
		return name;
	}
	
	public View getView() {
		initView();
		return view;
	}

}
