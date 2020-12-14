package com.onlineshop.onlineshop.jwt;



import com.onlineshop.onlineshop.exceptions.UnAuthorizedRequestException;
import com.onlineshop.onlineshop.services.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final CustomUserDetailsService customUserDetailsService;

    private final JwtUtil jwtUtil;

    @Autowired
    @Qualifier("handlerExceptionResolver")
    private HandlerExceptionResolver resolver;

    public JwtRequestFilter(CustomUserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.customUserDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }

    public void setErrorResponse(HttpStatus status, HttpServletResponse response, HttpServletRequest request, Throwable ex) {
        response.setStatus(status.value());
        response.setContentType("application/json");

        try {
            String json = response.getStatus() + "\n" + ex.getMessage() + "\n" +  request.getRequestURI();
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                jwtUtil.validateToken(jwt);
            } catch (UnAuthorizedRequestException exception) {
                setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, exception);
                return;
            }

            username = jwtUtil.extractUsername(jwt);
        } else {
            setErrorResponse(HttpStatus.UNAUTHORIZED, response, request, new UnAuthorizedRequestException("INVALID_TOKEN"));
            return;

        }
        UserDetails userDetails = null;
        try {
            userDetails = this.customUserDetailsService.loadUserByUsername(username);
        } catch (UsernameNotFoundException exception) {
            throw new UsernameNotFoundException(exception.getMessage());
        }
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        chain.doFilter(request, response);
    }
}