package com.it.server;


import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.it.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	public static final String APP_ROOT = "/";
	
	public static final String LOGIN_URI = APP_ROOT + "login";
	
	public static final String LOGOUT_URL = LOGIN_URI + "/logout";
	public static final String SIGN_OUT_URL = LOGOUT_URL + "?restartApplication";
	
	
	
	@Inject
	private UserService userService;

	
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeRequests()
            .antMatchers("/vaadinServlet/*", "/vaadinServlet/**", "/VAADIN/**", "/UIDL**", "/login/UIDL**", "/togglz-console**", "/login/**", "/rest/api/**", "/login/process").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                	.loginPage("/login/")
                	.failureUrl("/login/?status=failed")
                	.loginProcessingUrl("/login/process")
                	.defaultSuccessUrl("/")
                .permitAll()
                .and()
             .logout()
             	.logoutUrl(LOGOUT_URL)
             	.logoutSuccessUrl("/login")
             	.permitAll()
             	.and()
             .rememberMe()
             	.key("secretKeykey123!#@")
             	.tokenValiditySeconds(2592000);
                ;
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth, Md5PasswordEncoder encoder) throws Exception {
    	auth.userDetailsService(userService).passwordEncoder(encoder);
    }
    

}