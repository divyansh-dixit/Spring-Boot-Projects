package com.example.demo.BatchConfig;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.Entities.BusRecord;

public class CustomItemProcessor implements ItemProcessor<BusRecord, BusRecord> {

	@Override
	public BusRecord process(BusRecord item) throws Exception {
	 //Lets Process
		 if(item.getBusTimings().equalsIgnoreCase("Afternoon") || item.getBusRoute().equalsIgnoreCase("Route 2")) {
			 item.setRandomBus("Expensive Bus");
		 }
		
		return item;
	}

}
