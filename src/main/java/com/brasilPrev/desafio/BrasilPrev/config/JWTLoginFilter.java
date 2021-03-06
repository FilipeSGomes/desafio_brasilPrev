package com.brasilPrev.desafio.BrasilPrev.config;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.brasilPrev.desafio.BrasilPrev.model.Usuario;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	@Autowired
	private TokenAuthenticationService token;
	
	protected JWTLoginFilter(String url, AuthenticationManager authManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException, IOException, ServletException {
		Usuario users = new ObjectMapper().readValue(req.getInputStream(), Usuario.class);

		return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                        users.getUsername(),
                        users.getPassword(),
                        Collections.<GrantedAuthority>emptyList()
                )
        );
	}
	
	@Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res, FilterChain chain,
            Authentication auth
    ) throws IOException, ServletException {
        token.addAuthentication(res, auth.getName());//criar classe
    }

}
