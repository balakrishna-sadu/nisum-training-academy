package com.nisum.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.Course;
import com.nisum.model.Trainer;
import com.nisum.model.Training;
import com.nisum.repo.CoursesRepo;
import com.nisum.repo.TrainersRepo;
import com.nisum.repo.TrainingScheduleRepo;

@RestController
public class TrainingController {
	
	@Autowired
	TrainingScheduleRepo tsrepo;
	@Autowired
	TrainersRepo trepo;
	@Autowired
	CoursesRepo crepo;
	
	@PostMapping("/schedule-training")
	public String scheduleTraining(@RequestBody Training tr) {
		Training training = new Training();
		training.setId(tr.getId());
		training.setTrainer_name(tr.getTrainer_name());
		training.setCourse_name(tr.getCourse_name());
		training.setDate_time(tr.getDate_time());
		
		tsrepo.save(training);
		
		return "Training scheduled to "+ tr.getDate_time();
	}
	
	@GetMapping("/get-scheduled-trainings")
	public List<Training> getAllScheduledTrainings(){
		return tsrepo.findAll();
	}
	
	@GetMapping("/getfulldetails/{id}")
	public String getFullDetails(@PathVariable int id) {

		Optional<Training> t = tsrepo.findById(id);
		
		Trainer trainer = trepo.findByName(t.get().getTrainer_name());
		
		Course course = crepo.findByCoursename(t.get().getCourse_name());
		
		return "Scheduled Training\n"+"Training ID : "+t.get().getId()
				
				+"\n\nTrainer : \n"
				+"\t id:"+trainer.getTrainerId()
				+ "\t name:"+trainer.getName()
				+"\t dept:"+trainer.getDept()
				
				+"\n\n Course :\n"
				+"\t course id:"+course.getCourseId()
				+"\t course name:"+course.getCoursename()
				+"\n\n Date-Time : "+t.get().getDate_time();
	}
	
	@PutMapping("/takefeedbackfor/{trainername}")
	public Trainer feedBackforTrainer(@PathVariable String trainername,@RequestParam String[] feedback) {
		Trainer tr = trepo.findByName(trainername);
		tr.setFeedback(feedback);
		trepo.save(tr);
		return tr;
	}
	
	//Check : can we access another controller methods
	@Autowired
	Controller controller;
	@GetMapping("/checkthis")
	public List<Trainer> checking() {
		return this.controller.getAllTrainers();
	}
	
}
