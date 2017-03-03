package com.it.server;

import javax.inject.Inject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.togglz.core.manager.EnumBasedFeatureProvider;
import org.togglz.core.spi.FeatureProvider;
import org.togglz.core.user.UserProvider;
import org.togglz.spring.security.SpringSecurityUserProvider;

import com.it.bo.server.enums.MyFeatures;
import com.it.domain.User;
import com.it.service.UserService;

@SpringBootApplication
@Configuration
@EnableTransactionManagement
@ComponentScan("com.it")
@EnableJpaRepositories("com.it.repository")
@EntityScan("com.it.domain")
public class Application implements CommandLineRunner {
	 

	public static ConfigurableApplicationContext context;
	
	@Inject
	private UserService userService;

	public static void main(String[] args) {
		context = SpringApplication.run(Application.class, args);
	}
	
    @Bean 
    public Md5PasswordEncoder md5PasswordEncoder() {
    	return new Md5PasswordEncoder();
    }
    
    @SuppressWarnings("unchecked")
	@Bean
    public FeatureProvider featureProvider() {
        return new EnumBasedFeatureProvider(MyFeatures.class);
    }
    
    
	@Bean
	public UserProvider getUserProvider() {
		return new SpringSecurityUserProvider("ROLE_ADMIN");
	}
    
	@Override
	public void run(String... args) throws Exception {
    	User user = new User();
    	user.setUsername("dev");
    	user.setPassword("111111");
    	user.setFirstName("Dev");
    	user.setLastName("user");
    	user.setEmail("dev@user.com");
    	userService.addNewUser(user);
    	
    	
    	User user2 = new User();
    	user2.setUsername("user2");
    	user2.setPassword("111111");
    	user2.setFirstName("User");
    	user2.setLastName("non-admin");
    	user2.setEmail("user2@user.com");
    	userService.addNewUser(user2);
    	
    	
    	User user3 = new User();
    	user3.setUsername("user3");
    	user3.setPassword("111111");
    	user3.setFirstName("name");
    	user3.setLastName("last");
    	user3.setEmail("super@user.com");
    	userService.addNewUser(user3);
		
	}

}
