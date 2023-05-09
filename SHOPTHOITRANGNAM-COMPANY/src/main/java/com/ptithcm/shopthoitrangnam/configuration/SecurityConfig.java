package com.ptithcm.shopthoitrangnam.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ptithcm.shopthoitrangnam.enumeration.Role;

@Configuration
public class SecurityConfig {
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	AuthenticationSuccessHandler authenticationSuccessHandler;
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/js/**", "/css/**", "/img/**", "/dist/**").permitAll()
                                .requestMatchers("/", "/company/login", "/customer/login").permitAll()
                                .requestMatchers("/company/login/error", "/customer/login/error").permitAll()
                                .requestMatchers("/company/owner/**").hasRole(Role.OWNER.getCode())
                                .requestMatchers("/customer/**").hasRole(Role.CUSTOMER.getCode())
                                .requestMatchers("/company/teller/**").hasRole(Role.TELLER.getCode())
                                .requestMatchers("/company/ware-house-worker/**").hasRole(Role.WAREHOUSE_WORKER.getCode())
                                .anyRequest().authenticated()
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/company/login")
                                .successHandler(authenticationSuccessHandler)
                                .failureUrl("/company/login/error")
                                .loginProcessingUrl("/company/login")
                                .permitAll()
                )
                .authenticationProvider(authenticationProvider())
                .rememberMe(rememberMe -> 
                		rememberMe
                				.rememberMeParameter("JSESSION")
                )
                .logout(logout -> 
                		logout
                				.logoutSuccessUrl("/")
                				.deleteCookies("JSESSION")
                );
				
		return httpSecurity.build();
	}
	
	@Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
} 
