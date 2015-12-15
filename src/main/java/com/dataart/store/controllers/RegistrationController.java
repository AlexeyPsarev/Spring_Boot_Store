package com.dataart.store.controllers;

import com.dataart.store.domain.User;
import com.dataart.store.service.UserService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegistrationController
{
	@Resource
	private UserService service;
	
	@Autowired
	private PasswordEncoder encoder;
	
	private static final Pattern USERNAME_PATTERN = Pattern.compile(
		"^(?=.{4,20}$)(?![_.0-9])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$");
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.{8,}$)([\\S]*)$");
	private static final Pattern FULLNAME_PATTERN = Pattern.compile("^[a-zA-Z ]{1,}$");
		
	private static final String USERNAME_KEY = "username";
	private static final String PASSWORD_KEY = "password";
	private static final String FULLNAME_KEY = "fullname";
	private static final String CONFIRMATION_KEY = "confirmation";
	private static final String BAD_USERNAME_KEY = "badUsername";
	private static final String BAD_FULLNAME_KEY = "badFullname";
	private static final String BAD_PASSWORD_KEY = "badPassword";
	private static final String CONFIRMATION_ERROR_KEY = "confirmErr";
	private static final String CANNOT_CREATE_KEY = "cannotCreate";

	private static final String BAD_USERNAME = "Forbidden username. Please, try again";
	private static final String BAD_FULLNAME = "Forbidden full name. Please, try again";
	private static final String BAD_PASSWORD = "Forbidden password. Please, try again";
	private static final String CONFIRMATION_ERROR = "Password doesn't match the confirmation. Please, try again";
	private static final String CANNOT_CREATE = "User with such name already exists";

	@RequestMapping(value = "/registrationForm.htm", method = RequestMethod.GET)
	public String showRegistrationForm()
	{
		return "registration";
	}
	
	@RequestMapping(value = "/register.htm", method = RequestMethod.POST)
	public String performRegistration(
		HttpServletRequest request,
		ModelMap model,
		@ModelAttribute(USERNAME_KEY) String username,
		@ModelAttribute(FULLNAME_KEY) String fullname,
		@ModelAttribute(PASSWORD_KEY) String password,
		@ModelAttribute(CONFIRMATION_KEY) String confirmation
	)
	{
		boolean isCorrect = true;
		Matcher m;
		m = USERNAME_PATTERN.matcher(username);
		if (!m.matches())
		{
			model.put(BAD_USERNAME_KEY, BAD_USERNAME);
			isCorrect = false;
		}
		m = FULLNAME_PATTERN.matcher(fullname);
		if (!m.matches())
		{
			model.put(BAD_FULLNAME_KEY, BAD_FULLNAME);
			isCorrect = false;
		}
		m = PASSWORD_PATTERN.matcher(password);
		if (!m.matches())
		{
			model.put(BAD_PASSWORD_KEY, BAD_PASSWORD);
			isCorrect = false;
		}
		if (!password.equals(confirmation))
		{
			model.put(CONFIRMATION_ERROR_KEY, CONFIRMATION_ERROR);
			isCorrect = false;
		}
		if (!isCorrect)
			return "registration";

		User user = new User(username, encoder.encode(password), fullname);
		if (service.canCreate(user))
		{
			service.save(user);
			request.getSession(false).invalidate();
			request.getSession(true).setAttribute("userId", user.getId());
			model.clear();
			return "redirect:/home.htm";
		}
		else
		{
			model.put(CANNOT_CREATE_KEY, CANNOT_CREATE);
			return "registration";
		}
	}
}
