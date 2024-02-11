package com.example.demo.Controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.BusDto;
import com.example.demo.Entities.BusRecord;
import com.example.demo.Services.BusServiceImplementation;

@RestController
@RequestMapping(value="/BusRecord")
public class BusDemoController {

	@Autowired(required = true)
private JobLauncher jobLauncher;
	 
	@Autowired
	private Job job;
	
	@Autowired
	private BusServiceImplementation busService;
	
	@PostMapping(value="/PostNewBus/")
	public ResponseEntity<BusDto> PostBusReco(@RequestBody BusRecord bus){
	BusDto busDto=	this.busService.PostBusRecords(bus);
		
		return new ResponseEntity<BusDto>(busDto, HttpStatus.CREATED);
	}
	
	@PostMapping(value="/CSVBatchRunner/")
	public void BatchStarter(){
	JobParameters jobParameter = new JobParametersBuilder().addLong("startAt", System.currentTimeMillis()).toJobParameters();
	
	
	
		try {
			jobLauncher.run(job, jobParameter);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
