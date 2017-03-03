package com.it.bo.server.components;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

import com.it.bo.server.enums.TableColumn;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.data.util.filter.And;
import com.vaadin.ui.Table;
import com.vaadin.ui.themes.ValoTheme;


public class CustomTable<T> extends Table {

	private static final long serialVersionUID = -1780247960110934751L;
	
	List<Filter> tableFilters = new ArrayList<>();
	
	private Function<T, Object[]> rowMappingFunction;
	
	public CustomTable(Stream<TableColumn> columnHeaders, Function<T, Object[]> rowMappingFunction) {
		this.rowMappingFunction = rowMappingFunction;
		
		setWidth(100, Unit.PERCENTAGE);
    	addStyleName(ValoTheme.TABLE_BORDERLESS);
    	addStyleName(ValoTheme.TABLE_NO_HORIZONTAL_LINES);
    	addStyleName(ValoTheme.TABLE_COMPACT);
    	setSelectable(true);
        
        
		setFooterVisible(false);
		setMultiSelect(false);
		setImmediate(true);
        setEnabled(true);
        
        columnHeaders.forEach(column -> addContainerProperty(column, column.getClazz(), null, column.getCaption(), null, null));
	}
	
	public void populateTableData(Collection<T> tableData) {
        removeAllItems();
		tableData.forEach(tableDataItem -> addItem(rowMappingFunction.apply(tableDataItem), tableDataItem));
	}
	
	public void setTableFilter(Filter filter) {
		
		tableFilters.remove(filter);
		
		tableFilters.add(filter);
		
		IndexedContainer data = (IndexedContainer) getContainerDataSource();
		
		data.removeAllContainerFilters();
		data.addContainerFilter(new And(tableFilters.toArray(new Filter[tableFilters.size()])));
		
		sort();
	}
}
