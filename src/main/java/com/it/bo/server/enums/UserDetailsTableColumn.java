package com.it.bo.server.enums;


import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.vaadin.ui.Button;

public enum UserDetailsTableColumn implements TableColumn {
	
	ID("ID", Long.class),
	USERNAME("Username", Button.class),
	EMAIL("Email", String.class),
	IS_SUSPENDED("Suspended", Boolean.class),
	SUSPEND(StringUtils.EMPTY, Button.class)
	;
	
	
	private Class<?> clazz;
	private String caption;
	
	UserDetailsTableColumn(String caption, Class<?> clazz) {
		this.caption = caption;
		this.clazz = clazz;
	}
	
	
	public Class<?> getClazz() {
		return clazz;
	}
	
	public String getCaption() {
		return caption;
	}
	
	public static Stream<TableColumn> stream() {
		 return Arrays.stream(values());
	 }

}
