package com.example.demo.Entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="BusRecords")
@Data
public class BusRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	@Column(name="BusDriver")
	String BusDriver;
	String BusRoute;
	String BusColor;
	String BusType;
	String BusTimings;
	String RandomBus;
}
