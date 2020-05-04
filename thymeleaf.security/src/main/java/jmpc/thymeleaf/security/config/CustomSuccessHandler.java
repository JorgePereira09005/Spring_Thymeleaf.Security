package jmpc.thymeleaf.security.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jmpc.thymeleaf.security.entity.User;
import jmpc.thymeleaf.security.service.UserService;

@Component
public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
    private UserService userService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String userName = authentication.getName();
		
		User user = userService.findByUserName(userName);
		
		//place logged in user in the session
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
		
		//forward to home page after authentication
		response.sendRedirect(request.getContextPath() + "/");

	}

}
