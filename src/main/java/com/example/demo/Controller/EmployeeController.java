package com.example.demo.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Services.EmployeeServiceImpl;
import com.example.demo.Services.EmployeeServices;

@RestController
@RequestMapping("/EmployeeDetails")
public class EmployeeController {
	
	@Autowired
	private EmployeeServices employeeServices;
	
	@PostMapping("/PostImage")
	ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile file){
		String uploadedData = null;
		try {
			uploadedData = employeeServices.uploadImage(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(uploadedData);
	}
	
	@GetMapping("/getImage/{filename}")
ResponseEntity<?> downloadimage(@PathVariable String filename){
	byte[] image=this.employeeServices.DownloadImage(filename);
	
	return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image); 
}
}
