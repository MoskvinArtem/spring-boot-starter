package com.it.bo.server.views;


import java.util.function.Consumer;

import com.it.bo.server.dto.LoginDto;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Responsive;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class LoginView extends VerticalLayout {
	
	private static final long serialVersionUID = -7956458888160593235L;
	
	private String redirectUrl;
	
	Consumer<LoginDto> loginClickHandler;
	

    public LoginView(Consumer<LoginDto> loginClickHandler) {
    	this.loginClickHandler = loginClickHandler;
    	
        setSizeFull();
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);
    }
    
    protected Component buildErrorComponent() {
		Label label = new Label("Invalid credentials", ContentMode.HTML);
		VerticalLayout layout = new VerticalLayout();
		layout.addComponent(label);
		layout.addStyleName("error");
		return layout;
	}

    private Component buildLoginForm() {
        VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        
        Component errorComponent = buildErrorComponent();
        errorComponent.setVisible("failed".equals(VaadinService.getCurrentRequest().getParameter("status")));
        loginPanel.addComponent(errorComponent);
        return loginPanel;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");

        TextField username = new TextField("Username");
        username.setIcon(FontAwesome.USER);
        username.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        PasswordField password = new PasswordField("Password");
        password.setIcon(FontAwesome.LOCK);
        password.addStyleName(ValoTheme.TEXTFIELD_INLINE_ICON);

        Button signin = new Button("Sign In", (event) -> loginClickHandler.accept(new LoginDto(username.getValue(), password.getValue(), redirectUrl, true)));
        signin.addStyleName(ValoTheme.BUTTON_PRIMARY);
        signin.focus();

        fields.addComponents(username, password, signin);
        fields.setComponentAlignment(signin, Alignment.BOTTOM_LEFT);

        return fields;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label("Welcome");
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H4);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);

        return labels;
    }

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
		
	}

}