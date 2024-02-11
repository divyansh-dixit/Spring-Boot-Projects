package com.example.demo.Services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface EmployeeServices {

	String uploadImage(MultipartFile file) throws IOException;

	byte[] DownloadImage(String filename);
}
