package com.onlineshop.onlineshop.config;

import com.onlineshop.onlineshop.jwt.JwtRequestFilter;
import com.onlineshop.onlineshop.jwt.JwtUtil;
import com.onlineshop.onlineshop.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean
    public FilterRegistrationBean registerJwtFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        JwtRequestFilter jwtRequestFilter = new JwtRequestFilter(customUserDetailsService, new JwtUtil());
        filterRegistrationBean.setFilter(jwtRequestFilter);
        filterRegistrationBean.addUrlPatterns("/api/*");
        return filterRegistrationBean;
    }
}