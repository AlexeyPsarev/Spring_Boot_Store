package com.dataart.store.controllers;

import com.dataart.store.domain.User;
import com.dataart.store.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController
{
	@Resource
	private UserService service;
	
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";
	
	private static final String ERR_MSG_KEY = "errMsg";
	private static final String AUTH_FAILED = "Incorrect username or password. Please, try again";
	
	@RequestMapping(value = "/loginForm.htm", method = RequestMethod.GET)
	public String showLoginForm()
	{
		return "login";
	}
	
	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)
    public String performLogin(
		HttpServletRequest request,
		ModelMap model,
		@ModelAttribute(USERNAME_KEY) String username,
		@ModelAttribute(PASSWORD_KEY) String password
	)
	{
		if (service.authenticate(username, password))
		{
			User user = service.find(username);
			request.getSession(false).invalidate();
			request.getSession(true).setAttribute("userId", user.getId());
			model.clear();
			return "redirect:/home.htm";
		}
		else
		{
			model.put(ERR_MSG_KEY, AUTH_FAILED);
			return "login";
		}
    }
}
