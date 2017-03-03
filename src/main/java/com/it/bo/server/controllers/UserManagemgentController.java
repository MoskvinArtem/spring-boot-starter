package com.it.bo.server.controllers;


import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.PageRequest;

import com.it.bo.server.components.AddEditUserWindow;
import com.it.bo.server.components.ConfirmationWindow;
import com.it.bo.server.framework.BaseController;
import com.it.bo.server.framework.ControllingView;
import com.it.bo.server.utils.BackOfficeNotificationUtils;
import com.it.bo.server.views.UserManagementView;
import com.it.domain.User;
import com.it.service.UserService;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Button.ClickEvent;

@ControllingView(value = UserManagementView.NAME, defaultView = true)
public class UserManagemgentController extends BaseController<UserManagementView> {

	private static final long serialVersionUID = -8793603983778166260L;
	
	
	@Inject
	private UserService userService;

	@Override
	public void initView() {

		view = new UserManagementView(this::onEnterView, this::onAddUser, this::onEditUser, this::onSuspendUser);

	}

	private void onAddUser(ClickEvent event) {
		AddEditUserWindow.open(this::addUser);
	}

	private void onSuspendUser(ClickEvent clickEvent) {
		User targetUser = (User) clickEvent.getButton().getData();
		String action = targetUser.isEnabled() ? "suspend" : "restore";
		ConfirmationWindow.open("Are you sure you want to " + action + " this user?", this::suspendUser, targetUser);
	}

	private void onEnterView(ViewChangeEvent enterViewEvent) {
		refreshTable();
	}

	private void refreshTable() {
		List<User> users = getAllEntities();
		view.refreshView(users);
	}

	private void suspendUser(User user) {
		
	}

	private void addUser(User user) {
		userService.addNewUser(user);
		refreshTable();

		BackOfficeNotificationUtils.showSuccessButtomLineNotification("User added successfully");
	}
	
	private void editUser(User user) {
		userService.updateUser(user);
		refreshTable();

		BackOfficeNotificationUtils.showSuccessButtomLineNotification("User updated successfully");
	}
	
	
	private void onEditUser(ClickEvent clickEvent) {
		User userToEdit = (User) clickEvent.getButton().getData();
		AddEditUserWindow.open(userToEdit, this::editUser);
	}
	

	private List<User> getAllEntities() {
		PageRequest pr = new PageRequest(0, Integer.MAX_VALUE);
		return userService.getAllUsers(pr).getContent();
	}

}
