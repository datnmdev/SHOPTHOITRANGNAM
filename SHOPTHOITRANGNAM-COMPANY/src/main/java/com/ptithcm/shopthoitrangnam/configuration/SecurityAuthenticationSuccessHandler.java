package com.ptithcm.shopthoitrangnam.configuration;

import java.io.IOException;

import org.apache.catalina.authenticator.SavedRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
	        String targetUrl = determineTargetUrl(request, authentication);
	        if (response.isCommitted()) {
	            return;
	        }
	        redirectStrategy.sendRedirect(request, response, targetUrl);
	    }

	    protected String determineTargetUrl(HttpServletRequest request, Authentication authentication) {
	        String targetUrl = "/company/login";
	        HttpSession session = request.getSession(false);
	        if (session != null) {
	        	SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
	        	if (savedRequest != null) {
		            targetUrl = savedRequest.getRequestURI();
		        } else {
		            if (isOwner(authentication)) {
		                targetUrl = "/company/owner";
		            } else if (isTeller(authentication)) {
		                targetUrl = "/company/teller";
		            } else if (isWareHouseWorker(authentication)) {
		            	targetUrl = "/company/ware-house-worker";
		            }
		        }
	        }
	        return targetUrl;
	    }

	    protected boolean isOwner(Authentication authentication) {
	        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.OWNER.getCode()));
	    }

	    protected boolean isTeller(Authentication authentication) {
	        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.TELLER.getCode()));
	    }
	    
	    protected boolean isWareHouseWorker(Authentication authentication) {
	        return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(Role.WAREHOUSE_WORKER.getCode()));
	    }
}
