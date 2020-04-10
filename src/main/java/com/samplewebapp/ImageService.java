package com.samplewebapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageService {
	private static final String IMAGE_PATH = Paths.get(System.getProperty("user.dir"),
			"/src/main/webapp/resources/images/").toAbsolutePath().toString();
	
	public boolean writeFile(MultipartFile file, String name) {
		try {
			byte[] bytes = file.getBytes();
			Path savePath = Paths.get(IMAGE_PATH + "/" + name).toAbsolutePath();
			Files.write(savePath, bytes);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public void deleteImage(String name) {
		try {
			Paths.get(IMAGE_PATH + "/" + name).toFile().delete();
		} catch (Exception e) {}		
	}
	
	public String generateFileNameWithSuffix(MultipartFile file) {
		return file.getOriginalFilename();
	}

}
