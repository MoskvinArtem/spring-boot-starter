package com.it.bo.server.views;


import java.util.List;
import java.util.function.Consumer;

import com.it.bo.server.components.CustomTable;
import com.it.bo.server.enums.UserDetailsTableColumn;
import com.it.domain.User;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;


/**
 * User management screen
 * @author Evgenym
 *
 */
public class UserManagementView extends BaseView {
	
	private static final long serialVersionUID = -6020746693347808892L;
	
	public static final String NAME = "Users";
	
	
	private Button addNewUserButton;
	protected CustomTable<User> usersTable;
	
	private ClickListener suspendUserHandler;
	private ClickListener addUserHandler;
	private ClickListener editUserHandler;
	private Consumer<ViewChangeEvent> enterViewHandler;
	
	public UserManagementView(Consumer<ViewChangeEvent> enterViewHandler, ClickListener addUserHandler, ClickListener editUserHandler, ClickListener suspendUserHandler) {
		super(NAME);
		
		this.suspendUserHandler = suspendUserHandler;
		this.addUserHandler = addUserHandler;
		this.enterViewHandler = enterViewHandler; 
		this.editUserHandler = editUserHandler;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		enterViewHandler.accept(event);
		addNewUserButton.addClickListener(addUserHandler);
	}

	@Override
	protected Component buildViewBody() {
		VerticalLayout root = new VerticalLayout();
		root.setWidth(100.0f, Unit.PERCENTAGE);
		root.setSpacing(true);
		root.setMargin(true);
		root.addStyleName("profile-form");

		usersTable = new CustomTable<>(UserDetailsTableColumn.stream(), this::userToTableItem);
		Component toolBar = buildToolbar();
		
		root.addComponent(toolBar);
        root.addComponent(usersTable);
        
        return root;
	}
	
	private Component buildToolbar() {
		HorizontalLayout header = new HorizontalLayout();
		header.addStyleName("viewheader");
		header.setSpacing(true);
		Responsive.makeResponsive(header);

		addNewUserButton = new Button("Create user");
		addNewUserButton.setStyleName(ValoTheme.BUTTON_LINK);
		addNewUserButton.setIcon(FontAwesome.PLUS);
		HorizontalLayout toolsLayout = new HorizontalLayout(addNewUserButton);
		toolsLayout.setSpacing(true);
		toolsLayout.addStyleName("toolbar");
		header.addComponent(toolsLayout);

		return header;
	}
	
	private Object[] userToTableItem(User user) {
		
		Button userNameButton = new Button(user.getUsername(), editUserHandler);
		userNameButton.setData(user);
		userNameButton.setStyleName(ValoTheme.BUTTON_LINK);
		
		Button suspendBtn = new Button(user.isEnabled() ? "Suspend" : "Restore", suspendUserHandler);
		suspendBtn.setData(user);
		suspendBtn.setStyleName(ValoTheme.BUTTON_LINK);
		
		return new Object[] {
				user.getId(),
				userNameButton,
				user.getEmail(), 
				!user.isEnabled(),
				suspendBtn
				};
	}
	
	
	public void refreshView(List<User> users) {
		usersTable.populateTableData(users);
	}


}
