package com.example.demo.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Entities.BusRecord;

public interface BusRepo extends JpaRepository<BusRecord, Integer> {

}
