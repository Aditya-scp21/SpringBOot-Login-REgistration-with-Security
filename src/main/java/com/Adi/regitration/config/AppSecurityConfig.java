package com.Adi.regitration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.Adi.regitration.Service.CustomerService;

@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

	@Autowired
	private CustomerService customerService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authProvider() {
		DaoAuthenticationProvider authpro = new DaoAuthenticationProvider();
		authpro.setUserDetailsService(customerService);
		authpro.setPasswordEncoder(passwordEncoder());
		return authpro;
	}

	@Bean
	public AuthenticationManager Authmaneger(AuthenticationConfiguration authconfig) throws Exception {

		return authconfig.getAuthenticationManager();
	}
	@Bean
	public SecurityFilterChain securityconfig(HttpSecurity http) throws Exception{
		http .csrf(csrf -> csrf.disable())
		      .authorizeHttpRequests(req ->{
			req.requestMatchers("/register","/login")
			.permitAll()
			.anyRequest()
			.authenticated();
		});
		return http.build();
	}
	
}
