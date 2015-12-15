package com.dataart.store.controllers;

import com.dataart.store.domain.ApplicationPkg;
import com.dataart.store.service.ApplicationPkgService;
import java.io.IOException;
import javax.annotation.Resource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

@org.springframework.stereotype.Controller
@SessionAttributes("userId")
public class UploadController
{
	@Resource
	private ApplicationPkgService service;
		
	private static final String APP_NAME_KEY = "appName";
	private static final String PACKAGE_KEY = "pkg";
	private static final String CATEGORY_KEY = "category";
	private static final String DESCR_KEY = "description";
	
	private static final String NAME_ERR_KEY = "appNameErr";
	private static final String FILE_ERR_KEY = "appFileErr";
	
	private static final String EMPTY_NAME = "Application name is required";
	private static final String EMPTY_FILE = "Application file is required";
	
	@RequestMapping(value = "/upload.htm", method = RequestMethod.GET)
	public String getUploadPage(ModelMap model)
	{
		if (model.get("userId") == null)
		{
			model.clear();
			return "redirect:/";
		}
		return "upload";
	}
	
	@RequestMapping(value = "/doUpload.htm", method = RequestMethod.POST)
	public String handleFileUpload(
		@ModelAttribute(APP_NAME_KEY) String appName,
		@ModelAttribute(PACKAGE_KEY) MultipartFile pkg,
		@ModelAttribute(CATEGORY_KEY) String category,
		@ModelAttribute(DESCR_KEY) String description,
		@ModelAttribute("userId") Integer author,
		ModelMap model
	) throws IOException
	{
		if (author == null)
		{
			model.clear();
			return "redirect:/";
		}
		if (appName.isEmpty())
		{
			model.put(NAME_ERR_KEY, EMPTY_NAME);
			return "upload";
		}
		if (pkg.getSize() <= 0)
		{
			model.put(FILE_ERR_KEY, EMPTY_FILE);
			return "upload";
		}
		
		ApplicationPkg item = new ApplicationPkg();
		ApplicationPkgService.Status st = service.processZipFile(pkg, item, appName, category, description, author);
		if (st != ApplicationPkgService.Status.OK)
		{
			model.put(FILE_ERR_KEY, st.getMessage());
			return "upload";
		}
		service.upload(pkg, item);
		return "uploadDone";
	}
}
