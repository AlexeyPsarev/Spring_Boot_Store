package com.dataart.store.controllers;

import com.dataart.store.domain.User;
import com.dataart.store.service.UserService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userId")
public class ChangePasswordController
{
	@Autowired
	private PasswordEncoder encoder;
	
	@Resource
	private UserService service;
	
	private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.{8,}$)([\\S]*)$");
	
	private static final String OLD_PASSWORD_KEY = "oldPassword";
	private static final String NEW_PASSWORD_KEY = "password";
	private static final String CONFIRMATION_KEY = "confirmation";
	private static final String AUTH_FAILED_KEY = "oldPassErr";
	private static final String BAD_PASSWORD_KEY = "newPassErr";
	private static final String CONFIRMATION_ERROR_KEY = "confirmErr";
			
	private static final String AUTH_FAILED = "Incorrect password. Please, try again";
	private static final String BAD_PASSWORD = "Forbidden password. Please, try again";
	private static final String CONFIRMATION_ERROR = "Password doesn't match the confirmation. Please, try again";
	
	@RequestMapping(value = "/changePassword.htm", method = RequestMethod.GET)
	public String showPage(ModelMap model)
	{
		if (model.get("userId") == null)
		{
			model.clear();
			return "redirect:/";
		}
		return "password";
	}
	
	@RequestMapping(value = "/newPassword.htm", method = RequestMethod.POST)
	public String setNewPassword(
		@ModelAttribute(OLD_PASSWORD_KEY) String oldPassword,
		@ModelAttribute(NEW_PASSWORD_KEY) String password,
		@ModelAttribute(CONFIRMATION_KEY) String confirmation,
		ModelMap model)
	{
		Integer id = (Integer)model.get("userId");
		if (id == null)
		{
			model.clear();
			return "redirect:/";
		}
		
		User user = service.find(id);
		if (service.authenticate(user.getUsername(),oldPassword))
		{
			Matcher m = PASSWORD_PATTERN.matcher(password);
			if (!m.matches())
			{
				model.put(BAD_PASSWORD_KEY, BAD_PASSWORD);
				return "password";
			}
			if (!password.equals(confirmation))
			{
				model.put(CONFIRMATION_ERROR_KEY, CONFIRMATION_ERROR);
				return "password";
			}
			user.setPassword(encoder.encode(password));
			service.save(user);
			return "passwordChanged";
		}
		else
		{
			model.put(AUTH_FAILED_KEY, AUTH_FAILED);
			return "password";
		}
	}
}
