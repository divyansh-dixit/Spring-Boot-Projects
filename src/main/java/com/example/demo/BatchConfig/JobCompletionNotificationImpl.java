package com.example.demo.BatchConfig;

import org.hibernate.annotations.common.util.impl.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.CompositeJobExecutionListener;

import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationImpl extends CompositeJobExecutionListener {
	
	private org.jboss.logging.Logger lg=   LoggerFactory.logger(JobCompletionNotificationImpl.class);

	@Override
	public void afterJob(JobExecution jobExecution) {
	lg.info("Job Execution Started : ");
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
	if(jobExecution.getStatus()==BatchStatus.COMPLETED) {
		lg.info(" Job Completed ");
	}
	}
	

}
