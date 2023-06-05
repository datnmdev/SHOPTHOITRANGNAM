package com.ptithcm.shopthoitrangnam.configuration;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.ptithcm.shopthoitrangnam.enumeration.Role;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
	        handle(request, response, authentication);
	    }

	    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
	        String targetUrl = determineTargetUrl(request, response, authentication);
	        if (response.isCommitted()) {
	            return;
	        }
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }

	    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
	        String targetUrl = "/";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	            SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
	            if (savedRequest != null) {
	                targetUrl = savedRequest.getRedirectUrl();
	            } else {
	                targetUrl = "/user/home";
	            }
	        }
	        return targetUrl;
	    }
}
