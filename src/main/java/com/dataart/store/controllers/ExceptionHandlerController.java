package com.dataart.store.controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController
{
	private static final String ERR_MGS_KEY = "errMsg";
		
	@ExceptionHandler(Exception.class)
	public String handleException(Exception ex, Model model)
	{
		model.addAttribute(ERR_MGS_KEY, ex.getMessage());
		return "errorPage";
	}
}
