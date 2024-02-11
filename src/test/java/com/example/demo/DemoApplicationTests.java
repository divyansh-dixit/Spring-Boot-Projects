package com.example.demo;

import org.junit.jupiter.api.Test;
import org.modelmapper.internal.util.Assert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.demo.Entities.BusRecord;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Test
	void ContainValueinBody(@RequestBody BusRecord bus) {
		Assert.isNull(bus);
	}

}
