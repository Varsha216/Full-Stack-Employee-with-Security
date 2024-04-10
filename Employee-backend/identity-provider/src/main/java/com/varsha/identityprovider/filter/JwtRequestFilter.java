package com.varsha.identityprovider.filter;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.varsha.identityprovider.model.AdminProfile;
import com.varsha.identityprovider.service.AdminDetailsService;
import com.varsha.identityprovider.service.JwtService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtRequestFilter extends OncePerRequestFilter{

	private final AdminDetailsService adminDetailsService;
	private final JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		final String jwtRequestHeader = request.getHeader("Authorization");
		log.info(jwtRequestHeader);
		
		String jwt = null, username = null;
		if (jwtRequestHeader != null && jwtRequestHeader.startsWith("Bearer ")) {
			jwt = jwtRequestHeader.substring(7);
			try {
				username = jwtService.extractUsername(jwt);
			}
			catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} 
			catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}
		}else {
			log.warn("JWT Token does not begin with Bearer String");
		}
		
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			AdminProfile admin = adminDetailsService.loadUserByUsername(username);
			if (jwtService.validateToken(jwt, admin)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						admin, null, admin.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);
	}
}
