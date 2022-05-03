package com.training.springbootlogin.security;

import com.training.springbootlogin.config.SecurityConfigProperties;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class SimpleAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private SecurityConfigProperties config;

	@Override
	protected void doFilterInternal(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) throws IOException, ServletException { try {
//
		filterChain.doFilter(request, response);
	} catch(ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
	} }


}