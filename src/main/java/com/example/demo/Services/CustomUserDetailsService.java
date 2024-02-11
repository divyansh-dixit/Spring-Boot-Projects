package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import com.example.demo.Entities.Employee;
import com.example.demo.Repo.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
 
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		Employee emp =this.employeeRepository.findByName(username).orElseThrow(()-> new ResourceAccessException(username)); 
		return emp;
	}

}
