package com.it.bo.server.ui;

import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import com.it.bo.server.dto.LoginDto;
import com.it.bo.server.views.LoginView;
import com.it.server.SecurityConfig;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.JavaScript;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;


@Theme("dashboard")
@Title("Admin")
@SpringUI(path = SecurityConfig.LOGIN_URI)
public class LoginUI extends UI {
	


	private static final long serialVersionUID = -6883904319542790369L;
	
	private final static String FORM_JS = "document.body.innerHTML += '"
			+ "<form id=\"login_form\" action=\"/login/process\" method=\"post\">"
			+ "<input type=\"hidden\" name=\"username\" value=\"{username}\"/>"
			+ "<input type=\"hidden\" name=\"password\" value=\"{password}\"/>"
			+ "<input type=\"hidden\" name=\"_spring_security_remember_me\" value=\"{remember}\"/>"
			+ "<input type=\"hidden\" name=\"redirect_url\" value=\"{redirectUrl}\"/>"
			+ "</form>'; document.getElementById(\"login_form\")"
			+ ".submit();";

	
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	String redirectUrl = vaadinRequest.getParameter("v-loc").replace(SecurityConfig.LOGIN_URI, StringUtils.EMPTY);
    	setLocale(Locale.US);
        addStyleName(ValoTheme.UI_WITH_MENU);
    	addStyleName("loginview");
    	LoginView loginView = new LoginView(this::onUserLogin);
    	loginView.setRedirectUrl(redirectUrl);
        setContent(loginView);
    }

	public void onUserLogin(LoginDto loginDto) {
		String script = FORM_JS.replace("{username}", loginDto.getUsername())
				.replace("{password}", loginDto.getPassword())
				.replace("{remember}", String.valueOf(loginDto.isRememberMe()))
				.replace("{redirectUrl}", loginDto.getRedirectUrl());
		JavaScript.getCurrent().execute(script);
	}
}