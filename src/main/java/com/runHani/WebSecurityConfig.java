package com.runHani;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private AuthFailureHandler authFailureHandler;
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
			.antMatchers("/", "/notice/**", "/account/**" ,  "/account/login").permitAll()
			.anyRequest()
			.authenticated()
			.and()
		.formLogin()
			.loginPage("/account/login")
			.failureHandler(authFailureHandler)
			.permitAll()
			.and()
		.logout()
			.logoutUrl("/account/logout")
			.logoutSuccessUrl("/account/login")
			.permitAll()
			.and()
		.userDetailsService(userDetailsService);
				

			}
	

	@Override
	public void configure(WebSecurity web) throws Exception
	{
		web.ignoring().antMatchers("/resource/css/*",  "/resource/js/*");
	
	}
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.jdbcAuthentication()
	      .dataSource(dataSource)
	      .passwordEncoder(passwordEncoder())
	      .usersByUsernameQuery("select email,password,enable "
	        + "from tb_user_bas "
	        + "where email = ?")
	      .authoritiesByUsernameQuery("select email,authority "
	        + "from tb_userauth_bas "
	        + "where email = ?");
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {

	    return new BCryptPasswordEncoder();
	}

}