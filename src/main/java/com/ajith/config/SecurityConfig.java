package com.ajith.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ajith.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity

public class SecurityConfig {
	
	@Autowired
	AuthenticationSuccessHandler customsuccessHandler;
	
	@Bean
		UserDetailsService getUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider getDaoAuthProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getUserDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(getPasswordEncoder());

		return daoAuthenticationProvider;
	}
	@Bean
	SecurityFilterChain securityfilterchain(HttpSecurity http) throws Exception{
		http
			.authorizeHttpRequests(
					authorize -> authorize.antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**")
					.hasRole("USER").antMatchers("/**").permitAll()
					
					
					).formLogin(
							formlogin -> formlogin
							.loginPage("/signin")
							.loginProcessingUrl("/login")
							.successHandler(customsuccessHandler)
						
							
							
							).csrf(
									csrf -> csrf.disable()
									);
		return http.build();
	}

}
//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//	auth.authenticationProvider(getDaoAuthProvider());
//}
//
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//
//	http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/user/**").hasRole("USER")
//			.antMatchers("/**").permitAll().and().formLogin().loginPage("/signin").loginProcessingUrl("/login")
//			.defaultSuccessUrl("/user/").and().csrf().disable();
//
//}