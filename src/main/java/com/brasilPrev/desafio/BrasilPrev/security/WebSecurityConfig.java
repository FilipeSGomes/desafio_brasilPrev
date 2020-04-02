package com.brasilPrev.desafio.BrasilPrev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.brasilPrev.desafio.BrasilPrev.config.ComercialUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ComercialUserDetailsService uds;
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers(HttpMethod.GET, "/swagger-ui.html").permitAll()
			.antMatchers(HttpMethod.GET, "/prev").permitAll()
			.antMatchers(HttpMethod.POST, "/prev/login").permitAll()
			.anyRequest().authenticated();
//			.and()
			
			// filtra requisições de login
//			.addFilterBefore(new JWTLoginFilter("/prev/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class);
			
			// filtra outras requisições para verificar a presença do JWT no header
//			.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(uds)
			.passwordEncoder(new BCryptPasswordEncoder());
	}

}