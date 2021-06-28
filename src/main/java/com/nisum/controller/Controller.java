package com.nisum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.Course;
import com.nisum.model.Trainer;
import com.nisum.repo.CoursesRepo;
import com.nisum.repo.TrainersRepo;

@RestController
public class Controller {
	Controller(){}
	
	@Autowired
	TrainersRepo trepo;
	@Autowired
	CoursesRepo crepo;
	
	
	//*************** TRAINER *****************//
	@GetMapping("/trainers")
	public List<Trainer> getAllTrainers() {
		return trepo.findAll();
	}

	@PostMapping("/trainers")
	public String insertTrainer(@RequestBody Trainer tr) {
		trepo.save(tr);
		return "Trainer : " + tr.getName() + " inserted sucessfully";
	}

	@GetMapping("/trainer/{name}")
	public Trainer getTrainer(@PathVariable String name) {
		return trepo.findByName(name);
	}
	
	@PutMapping("/updatetrainer/{id}")
	public Trainer updateTrainerfeedback(@PathVariable int id,@RequestBody Trainer trainer) {
		Optional<Trainer> tr = trepo.findById(id);

		tr.get().setName(trainer.getName());
		tr.get().setDept(trainer.getDept());
		tr.get().setFeedback(trainer.getFeedback());
		trepo.save(tr.get());
		return tr.get();
	}
	
	@DeleteMapping("/deletetrainer/{id}")
	public String deleteTrainerbyId(@PathVariable int id) {
		Optional<Trainer> t=trepo.findById(id);
		trepo.deleteById(id);
		return "Trainer : "+t.get().getName()+" deleted !!";
	}

	//*********************** COURSE **************************//
	@GetMapping("/courses")
	public List<Course> getAllCourses() {
		return crepo.findAll();
	}

	@PostMapping("/courses")
	public String insretCourse(@RequestBody Course cr) {
		crepo.save(cr);
		return cr.getCoursename() + " Course inserted successfully";
	}
	@DeleteMapping("/deletecourse/{name}")
	public String deleteCourseByName(@PathVariable String name) {
		Course c = crepo.findByCoursename(name);
		trepo.deleteById(c.getCourseId());
		return "Course : "+c.getCoursename()+" deleted !!";
	}
	

}
