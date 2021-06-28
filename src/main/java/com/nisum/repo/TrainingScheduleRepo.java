package com.nisum.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.model.Training;

public interface TrainingScheduleRepo extends MongoRepository<Training, Integer>{
	
	
	public Training findByCourseName(String courseName);
	
	public void deleteByCourseName(String courseName);
	
	public List<Training> findByStatus(String status);
	
	public Training findByTrainerName(String trainer);

}
