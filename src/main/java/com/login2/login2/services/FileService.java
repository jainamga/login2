package com.login2.login2.services;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
 String UploadImage(String path,MultipartFile file) throws IOException;
 InputStream getResource(String filePath,String fileName) throws FileNotFoundException; 
}
