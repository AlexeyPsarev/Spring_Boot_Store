package com.dataart.store.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController
{
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public String handleRequest(HttpSession session)
	{
		session.invalidate();
		return "redirect:/";
	}
}
