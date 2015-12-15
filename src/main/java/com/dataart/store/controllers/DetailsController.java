package com.dataart.store.controllers;

import com.dataart.store.domain.ApplicationPkg;
import com.dataart.store.service.ApplicationPkgService;
import com.dataart.store.service.UserService;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userId")
public class DetailsController
{
	@Resource
	private ApplicationPkgService appService;
	
	@Resource
	private UserService userService;

	private static final String POPULAR_KEY = "popularApps";
	private static final String AUTHOR_KEY = "author";
	private static final String APPLICATION_ID_KEY = "app";
	private static final String APPLICATION_KEY = "app";
		
	@RequestMapping(value = "/details.htm", method = RequestMethod.GET)
	public String handleRequest(ModelMap model, @ModelAttribute(APPLICATION_ID_KEY) Integer id)
	{
		if (model.get("userId") == null)
		{
			model.clear();
			return "redirect:/";
		}	
		
		model.put(POPULAR_KEY, appService.findMostPopular().iterator());
		ApplicationPkg pkg = appService.findById(id);
		model.put(APPLICATION_KEY, pkg);
		model.put(AUTHOR_KEY, userService.find(pkg.getAuthor()));
		return "details";
	}
}
