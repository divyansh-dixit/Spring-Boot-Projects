package com.example.demo;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.Entities.BusRecord;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);

		//csd.loadUserByUsername("Divyansh");
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		
	}

	@Bean
	public ModelMapper ModelMp() {
		return new ModelMapper();
	}

}
