package com.example.demo.Repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	
	Optional<Employee> findByName(String Name);
	
}
