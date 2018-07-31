package com.marse.martian.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.marse.martian.security.filters.Http401AuthenticationEntry;
import com.marse.martian.security.filters.SHA256PasswordEncoder;
import com.marse.martian.security.filters.StatelessAuthenticationFilter;

@Configuration
@EnableWebSecurity
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	public static final String AUTHORIZED_ROLE = "authorizedRoleName";

	private final UserDetailsService userService;
	private final StatelessAuthenticationFilter statelessAuthenticationFilter;

	private SHA256PasswordEncoder sha256PasswordEncoder;

	@Autowired
	public SecurityConfig(UserDetailsService userService, StatelessAuthenticationFilter statelessAuthenticationFilter,
			SHA256PasswordEncoder sha256PasswordEncoder) {
		super(true);
		this.userService = userService;
		this.statelessAuthenticationFilter = statelessAuthenticationFilter;
		this.sha256PasswordEncoder = sha256PasswordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// we use jwt so that we can disable csrf protection
		http.csrf().disable().cors().disable();

		http.exceptionHandling().and().anonymous().and().servletApi().and().headers().cacheControl();

		http.authorizeRequests()
				// .antMatchers(HttpMethod.GET, "/transactions/list").hasRole(AUTHORIZED_ROLE)
		         .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
		         .antMatchers(HttpMethod.POST, "/api/wallet").permitAll()
				 //.anyRequest().fullyAuthenticated()
				.and().exceptionHandling()
				.authenticationEntryPoint(new Http401AuthenticationEntry("'Bearer token_type=\"JWT\"'"));
		http.addFilterBefore(statelessAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * Prevent StatelessAuthenticationFilter will be added to Spring Boot filter
	 * chain. Only Spring Security must use it.
	 */
	@Bean
	public FilterRegistrationBean<StatelessAuthenticationFilter> registration(StatelessAuthenticationFilter filter) {
		FilterRegistrationBean<StatelessAuthenticationFilter> registration = new FilterRegistrationBean<StatelessAuthenticationFilter>(
				filter);
		registration.setEnabled(false);
		return registration;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(sha256PasswordEncoder);
	}

	@Override
	protected UserDetailsService userDetailsService() {
		return userService;
	}

}
