package jmpc.thymeleaf.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jmpc.thymeleaf.security.service.UserService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	// reference to security data source
    @Autowired
    private UserService userService;
	
    @Autowired
    private CustomSuccessHandler customAuthenticationSuccessHandler;
    
   @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
   
   @Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/").hasRole("EMPLOYEE")
			.antMatchers("/managers/**").hasRole("MANAGER")
			.antMatchers("/admins/**").hasRole("ADMIN")
			.and()
			.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
			.and()
			.logout().permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/access-denied");
		
	}
   

 	@Bean
 	public BCryptPasswordEncoder passwordEncoder() {
 		return new BCryptPasswordEncoder();
 	}
 	
 	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
 		
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		
		auth.setUserDetailsService(userService); //set the custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //set the password encoder to use bcrypt
		
		return auth;
	}
	
}
