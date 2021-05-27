package com.nisum.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nisum.model.Course;

@Repository
public interface CoursesRepo extends MongoRepository<Course, Integer>{

	public Course findByCoursename(String name);
}
