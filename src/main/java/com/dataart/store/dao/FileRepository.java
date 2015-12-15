package com.dataart.store.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import org.apache.commons.io.FilenameUtils;
import org.h2.store.fs.FileUtils;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public class FileRepository
{
	private String realAppPath;
	private ResourceBundle bundle;
	private String osImgDir;
	private String osPkgDir;
	private String realPkgDirPath;
	private String realImgDirPath;
	
	private static final boolean IS_WINDOWS = System.getProperty("os.name").startsWith("Windows");
	private static final String DEFAULT_IMG_128 = "/images/default128.png";
	private static final String DEFAULT_IMG_512 = "/images/default512.png";
	private static final String IMG_REQUEST = "/image.htm/?name=";
	private static final int BUFFER_SIZE = 512;
	
	@PostConstruct
	public void createDirs()
	{
		bundle = ResourceBundle.getBundle("directories");
		try {
			realAppPath = bundle.getString("STORAGE");
		} catch (MissingResourceException e) {
			realAppPath = System.getProperty("java.io.tmpdir");
		}
		osImgDir = IS_WINDOWS ? bundle.getString("IMG_DIR").replace("/", "\\") : bundle.getString("IMG_DIR");
		osPkgDir = IS_WINDOWS ? bundle.getString("PKG_DIR").replace("/", "\\") : bundle.getString("PKG_DIR");
		realPkgDirPath = realAppPath + osPkgDir + File.separator;
		realImgDirPath = realAppPath + osImgDir + File.separator;
				
		File f = new File(realAppPath + osPkgDir);
		f.mkdirs();
		f = new File(realAppPath + osImgDir);
		f.mkdirs();
	}
	
	public String getRealImgDirPath()
	{
		return realImgDirPath;
	}
	
	public String saveImage(String pkgName, String baseName, byte[] data) throws IOException
	{
		String dirName = FilenameUtils.removeExtension(pkgName);
		String absDirPath = realImgDirPath + dirName;
		Files.createDirectories(Paths.get(absDirPath));
		try (FileOutputStream f = new FileOutputStream(absDirPath + File.separator + baseName)) {
			f.write(data);
		}
		return IMG_REQUEST + dirName + "/" + baseName;
	}
	
	public void saveMultipartFile(MultipartFile file) throws IOException
	{
		String filename = realPkgDirPath + file.getOriginalFilename();
		file.transferTo(new File(filename));
	}
	
	public String getDefaultPicture128Path()
	{
		return DEFAULT_IMG_128;
	}
	
	public String getDefaultPicture512Path()
	{
		return DEFAULT_IMG_512;
	}
	
	public void download(String pkgName, OutputStream out) throws IOException
	{
		String path = realPkgDirPath + pkgName;
		try (InputStream in = new FileInputStream(path)) {
			byte[] buffer = new byte[BUFFER_SIZE];
			int len;
			while ((len = in.read(buffer)) != -1)
				out.write(buffer, 0, len);
		}
	}
	
	public boolean fileExists(String name)
	{
		return FileUtils.exists(realPkgDirPath + name);
	}
	
	public long getFileSize(String name)
	{
		return FileUtils.size(realPkgDirPath + name);
	}
}
