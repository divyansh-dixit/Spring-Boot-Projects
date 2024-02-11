package com.example.demo.BatchConfig;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;

import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.data.builder.RepositoryItemWriterBuilder;

import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.example.demo.Entities.BusRecord;
import com.example.demo.Repo.BusRepo;

@Configuration
@EnableBatchProcessing
public class Config{

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory steBuilderFactory;
	
	@Autowired
	private BusRepo busRepo;
	//item read
	@Bean
	public ItemReader<BusRecord> itemReader(){
		return new FlatFileItemReaderBuilder().name("Csv-Reader").resource(new ClassPathResource("bus.csv")).linesToSkip(1).lineMapper(linemapper()).build();
		
	}
	
	private LineMapper<BusRecord> linemapper(){
		//initialise default line mapper
		DefaultLineMapper<BusRecord> lineMapper = new DefaultLineMapper<>();
		//line tokenizer
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		//set beanwrapper for target settings
		lineTokenizer.setStrict(false);
		lineTokenizer.setNames("id","busDriver","busRoute","busColor","busType","busTimings");
		lineTokenizer.setDelimiter(",");
		BeanWrapperFieldSetMapper<BusRecord> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
		
		beanWrapperFieldSetMapper.setTargetType(BusRecord.class);
		//set both in mapper
		lineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		lineMapper.setLineTokenizer(lineTokenizer);
		// return line mapper 
		return lineMapper;
	}
	
	//item processor
	@Bean
	public ItemProcessor<BusRecord, BusRecord> itemProcessor(){
		return  new CustomItemProcessor();
	}
	
	//item writer
	@Bean
	public ItemWriter<BusRecord> itemWriter(){
		return new RepositoryItemWriterBuilder().repository(busRepo).methodName("save").build();
	}
	
	//job 

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").flow(step()).end().build();
		
	}
	  
	//step
	@Bean
	public Step step()	 {
		return steBuilderFactory.get("step").<BusRecord,BusRecord>chunk(10).reader(itemReader()).processor(itemProcessor()).writer(itemWriter()).build();
	}
}
