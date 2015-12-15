package com.dataart.store.controllers;

import com.dataart.store.dao.FileRepository;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ImageController
{
	@Autowired
	private FileRepository repository;
	
	@RequestMapping(value = "/image.htm", method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE)
	public byte[] getImage(@RequestParam(value = "name") String name)
	{
		try {
			FileInputStream in = new FileInputStream(repository.getRealImgDirPath() + name);
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			return null;
		}
		
	}
}
