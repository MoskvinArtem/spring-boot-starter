package com.it.bo.server.enums;

/**
 * Interface for table columns that allows {@link com.it.bo.server.components.CustomTable} to populate data 
 * @author Evgenym
 *
 */
public interface TableColumn {
	
	Class<?> getClazz();
	String getCaption();


}
