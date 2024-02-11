package com.example.demo.Services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.BusDto;
import com.example.demo.Entities.BusRecord;
import com.example.demo.Repo.BusRepo;
@Service
public class BusServiceImplementation implements BusService{
	
	@Autowired
	private BusRepo BusRepository;
	
	@Autowired
	private ModelMapper mp;

	@Override
	public BusDto PostBusRecords(BusRecord bus) {
		// TODO Auto-generated method stub
		
		bus.setBusColor(bus.getBusColor());
		bus.setBusDriver(bus.getBusDriver());
		bus.setBusRoute(bus.getBusRoute());
		bus.setBusTimings(bus.getBusTimings());
		bus.setBusType(bus.getBusType());
		this.BusRepository.save(bus);
		BusDto busdto= this.mp.map(bus, BusDto.class);
		
		
		return busdto;
		
	}

}
