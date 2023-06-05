package com.ptithcm.shopthoitrangnam.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ptithcm.shopthoitrangnam.enumeration.Role;

@Configuration
public class SecurityConfig {
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/js/**", "/css/**", "/img/**", "/dist/**", "/logo/**", "/orthers/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/", "/products/**", "/sales/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/forgot-password/**", "/reset-password/**").permitAll()
                                .requestMatchers("/user/**").hasAuthority(Role.CUSTOMER.getCode())
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .successHandler(authenticationSuccessHandler)
                                .failureUrl("/login?error")
                                .permitAll()
                )
                .authenticationProvider(daoAuthenticationProvider())
                .rememberMe(rememberMe -> 
                		rememberMe
                				.rememberMeParameter("JSESSION")
                )
                .logout(logout -> 
                		logout
                				.logoutUrl("/logout")
                				.logoutSuccessUrl("/")
                				.deleteCookies("JSESSION")
                );
				
		return httpSecurity.build();
	}
	
	@Bean
	DaoAuthenticationProvider daoAuthenticationProvider() {
		SecurityAuthenticationProvider securityAuthenticationProvider = new SecurityAuthenticationProvider();
		securityAuthenticationProvider.setUserDetailsService(userDetailsService);
		securityAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return securityAuthenticationProvider;
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
} 
