package com.soundconnect.Configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
public class SpringSecurity extends WebSecurityConfigurerAdapter{
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery(
		"select username, password, enabled from users where username=?")
		.authoritiesByUsernameQuery(
		"select username, role from users where username=?");
	} 
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		http
	    // #1
	    .formLogin()
	        // #2
	        .loginPage("/login")
	        .failureUrl("/login?error")
	        // #3
	        .and()
	    // #4
	    .authorizeRequests()
	        // #5
//	        .antMatchers("/signup","/about").permitAll()
//	        .antMatchers("/admin/**").hasRole("ADMIN")
	        .anyRequest().authenticated();
	}
}
