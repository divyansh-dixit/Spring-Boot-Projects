package com.example.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDto {
	String Name;
	String Department;
	String	Designation;
	String	Role;
	byte[]	Photos;
}
