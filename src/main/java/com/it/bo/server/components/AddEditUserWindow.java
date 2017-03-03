package com.it.bo.server.components;


import java.util.function.Consumer;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;

import com.it.bo.server.utils.BackOfficeNotificationUtils;
import com.it.domain.User;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.data.fieldgroup.FieldGroup.CommitEvent;
import com.vaadin.data.fieldgroup.FieldGroup.CommitException;
import com.vaadin.data.fieldgroup.FieldGroup.CommitHandler;
import com.vaadin.data.fieldgroup.PropertyId;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

public class AddEditUserWindow extends BaseWindow {

	private static final long serialVersionUID = -1036324103379448772L;

	protected static final Logger logger = LoggerFactory.getLogger(AddEditUserWindow.class);

	private BeanFieldGroup<User> fieldGroup;
	private Consumer<User> saveButtonClickHandler;
	private User user;

	@PropertyId("username")
	private TextField username;

	@PropertyId("email")
	private TextField email;

	@PropertyId("password")
	private PasswordField password;

	@PropertyId("role")
	private ComboBox role;

	public AddEditUserWindow(User currentUser, Consumer<User> saveButtonClickHandler) {
		super(currentUser == null ? "Create user" : "Edit user", "Close", "Ok");

		setWidth(400, Unit.PIXELS);
		this.saveButtonClickHandler = saveButtonClickHandler;
		this.user = currentUser;

		init();

		if (user == null) {
			user = new User();
		}

		buildBeanFieldGroup();
	}

	@Override
	protected Component getBody() {
		VerticalLayout layout = new VerticalLayout();
		layout.setSizeFull();
		layout.setMargin(true);
		layout.setSpacing(true);

		
		
		email = initField("Email");

		password = new PasswordField("password");
		password.setNullRepresentation(StringUtils.EMPTY);
		password.setRequired(true);



		layout.addComponent(buildUsername());
		layout.addComponent(email);
		layout.addComponent(password);

		return layout;
	}

	private Component buildUsername() {
		username = initField("Username");
		return wrapToModeConditionalLayout(username);
	}
	
	
	private Component wrapToModeConditionalLayout(Component component) {
		HorizontalLayout layout = new HorizontalLayout();
		
		layout.addComponent(component);
		layout.setEnabled(user == null);
		
		return layout;
	}
	
	

	@Override
	protected void onSubmitButtonClick(ClickEvent clickEvent) {
		try {
			fieldGroup.commit();
			close();
		} catch (CommitException e) {
			String errorMessage = "Please fill all mandatory fields";
			if(e.getCause() instanceof DataIntegrityViolationException) {
				errorMessage = "User already exists";
			}
			BackOfficeNotificationUtils.showErrorNotification(errorMessage);
			logger.debug("Error creating user. {}", e.getMessage());
		}
	}

	private TextField initField(String caption) {
		TextField field = new TextField(caption);
		field.setNullRepresentation(StringUtils.EMPTY);
		field.setRequired(true);

		return field;
	}

	private void buildBeanFieldGroup() {
		fieldGroup = new BeanFieldGroup<User>(User.class);
		fieldGroup.bindMemberFields(this);
		fieldGroup.setItemDataSource(user);
		fieldGroup.addCommitHandler(new CommitHandler() {

			private static final long serialVersionUID = -381150301135009927L;

			@Override
			public void preCommit(CommitEvent commitEvent) throws CommitException {
			}

			@Override
			public void postCommit(CommitEvent commitEvent) throws CommitException {
				User user = fieldGroup.getItemDataSource().getBean();
				saveButtonClickHandler.accept(user);

			}
		});
	}


	public static void open(Consumer<User> submitClickHandler) {
		open(null, submitClickHandler);
	}

	public static void open(User user, Consumer<User> submitClickHandler) {

		AddEditUserWindow window = new AddEditUserWindow(user, submitClickHandler);
		UI.getCurrent().addWindow(window);
		window.focus();
	}

}
