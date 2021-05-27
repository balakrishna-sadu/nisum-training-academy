package com.nisum.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.model.Trainer;

@Repository
public interface TrainersRepo extends MongoRepository<Trainer, Integer>{

	public Trainer findByName(String name);
}
