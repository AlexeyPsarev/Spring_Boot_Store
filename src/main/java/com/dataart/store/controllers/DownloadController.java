package com.dataart.store.controllers;

import com.dataart.store.domain.ApplicationPkg;
import com.dataart.store.service.ApplicationPkgService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DownloadController
{
	@Resource
	private ApplicationPkgService appService;
	
	private static final String CONTENT_TYPE = "application/octet-stream";
	private static final String PKG_NOT_FOUND = "Cannot find package";
	private static final String APPLICATION_ID_KEY = "app";
	
	@RequestMapping(value = "/download.htm", method = RequestMethod.GET)
	public void download(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int id = Integer.parseInt(request.getParameter(APPLICATION_ID_KEY));
		ApplicationPkg pkg = appService.findById(id);
		if (!appService.fileExists(pkg))
			throw new FileNotFoundException(PKG_NOT_FOUND);
		int size = (int)appService.getFileSize(pkg);
		try (OutputStream outStream = response.getOutputStream()) {
			response.setContentType(CONTENT_TYPE);
			response.setContentLength(size);
			response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", pkg.getPkgName()));
			appService.download(pkg, outStream);
		}
	}
}
