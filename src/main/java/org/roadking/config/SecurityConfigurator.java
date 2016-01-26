package org.roadking.config;


import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;

import org.roadking.security.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfigurator extends WebSecurityConfigurerAdapter {
	

	@Autowired
	private CustomUserDetailService customUserDetailsSevice;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http.authorizeRequests().antMatchers("/console/**").permitAll();
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		// static resources
		http.authorizeRequests()
		.antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**").permitAll();
		
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/signin").anonymous()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/signin")
				//.loginProcessingUrl("/sign-in-process.html")
				//.failureUrl("/signin?error")
				.usernameParameter("username")
				.passwordParameter("password")
				//.defaultSuccessUrl("/admin/dashboard.html", true)
				.and()
			.logout()
				.logoutSuccessUrl("/signin?logout");
		
		http.exceptionHandling().accessDeniedPage("/admin/dashboard.html");
		http.sessionManagement().invalidSessionUrl("/signin");
		
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		//System.out.println("Authenication called");
		//auth.userDetailsService(customUserDetailsSevice);
		
		// In case of password encryption - for production site
		auth.userDetailsService(customUserDetailsSevice).passwordEncoder(passwordEncoder());
	}
	
	
	@Bean
	public FilterRegistrationBean getSpringSecurityFilterChainBindedToError(
	                @Qualifier("springSecurityFilterChain") Filter springSecurityFilterChain) {

	        FilterRegistrationBean registration = new FilterRegistrationBean();
	        registration.setFilter(springSecurityFilterChain);
	        registration.setDispatcherTypes(EnumSet.allOf(DispatcherType.class));
	        return registration;
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
}
