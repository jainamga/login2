package com.login2.login2.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service	
public class FileServiceImp implements FileService{

	@Override
	public String UploadImage(String path, MultipartFile file) throws IOException {
String name = file.getOriginalFilename();
String randomId=UUID.randomUUID().toString();
String fileName1=randomId.concat(name.substring(name.lastIndexOf(".")));
String filePath = path+File.pathSeparator+fileName1;
File f = new File(path);
if(!f.exists()) {
	
	f.mkdir();
}
Files.copy(file.getInputStream(), Paths.get(filePath));
return name;
	}

	@Override
	public InputStream getResource(String filePath, String fileName) throws FileNotFoundException {
		String fullPath=filePath+File.separator+fileName;
		InputStream is = new FileInputStream(fullPath);
		return is; 
		
	}

}
