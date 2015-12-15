package com.dataart.store.controllers;

import com.dataart.store.service.ApplicationPkgService;
import javax.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("userId")
public class HomeController
{
	@Resource
	private ApplicationPkgService service;
	
	private static final String DEFAULT_CATEGORY = "Games";
	private static final String CATEGORY_KEY = "category";
	private static final String PAGE_NUM_KEY = "page";
	private static final String POPULAR_KEY = "popularApps";
	private static final String PAGE_COUNT_KEY = "pageCount";
	private static final String APPLICATIONS_KEY = "apps";
		
	@RequestMapping(value = "/home.htm", method = RequestMethod.GET)
	public String handleRequest(
		ModelMap model,
		@ModelAttribute(CATEGORY_KEY) String category,
		@ModelAttribute(PAGE_NUM_KEY) String pageNum
	)
	{
		if (model.get("userId") == null)
		{
			model.clear();
			return "redirect:/";
		}
		
		if (category.equals(""))
		{
			category = DEFAULT_CATEGORY;
			model.put(CATEGORY_KEY, category);
		}
		if (pageNum.equals(""))
		{
			pageNum = "1";
			model.put(PAGE_NUM_KEY, pageNum);
		}
		model.put(POPULAR_KEY, service.findMostPopular().iterator());
		Page apps = service.find(category, Integer.parseInt(pageNum));
		model.put(APPLICATIONS_KEY, apps.iterator());
		model.put(PAGE_COUNT_KEY, service.pageCount(category));
		
		return "home";
	}
}
