package com.example.demo.Services;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.EmployeeDto;
import com.example.demo.Entities.Employee;
import com.example.demo.Repo.EmployeeRepository;
import com.example.demo.Utils.ImageUtils;

@Service

public class EmployeeServiceImpl implements EmployeeServices{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
public String uploadImage(MultipartFile file) throws IOException {

	Employee emp = this.employeeRepository.save(Employee.builder().name(file.getName()).type(file.getContentType()).Photos(ImageUtils.compressImage(file.getBytes())).build()); 
	if(emp!=null) {
		return "file is uploaded succesfully "+ file.getName();
	}
	else {
	return "file uploaded failure";
	}
}

@Override
public byte[] DownloadImage(String filename) {
	// TODO Auto-generated method stub
	Optional<Employee> Empimage  = this.employeeRepository.findByName(filename);
	byte[] image = ImageUtils.decompressImage(Empimage.get().getPhotos());
	return image;
}
}