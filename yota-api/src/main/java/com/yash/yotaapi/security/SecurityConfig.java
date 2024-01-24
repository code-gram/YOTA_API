package com.yash.yotaapi.security;
 
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
 
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
 
	private final JWTAuthenticationFilter jwtAuthenticationFilter;
	private final YotaUserDetailsService yotaUserDetailsService;
	private final JwtAuthEntryPoint jwtAuthEntryPoint;
 
	public SecurityConfig(JWTAuthenticationFilter jwtAuthenticationFilter,
			YotaUserDetailsService yotaUserDetailsService, JwtAuthEntryPoint jwtAuthEntryPoint) {
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.yotaUserDetailsService = yotaUserDetailsService;
		this.jwtAuthEntryPoint = jwtAuthEntryPoint;
	}
 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				.antMatchers(HttpMethod.POST, "/users/register", "/users/authenticate").permitAll().anyRequest()
				.authenticated()
				.and().exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
 
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
 
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
 
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}