package com.nisum.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nisum.model.Course;
import com.nisum.model.Trainer;
import com.nisum.model.Training;
import com.nisum.repo.CoursesRepo;
import com.nisum.repo.TrainersRepo;
import com.nisum.repo.TrainingScheduleRepo;

@RestController
@RequestMapping("/training-scheduler")
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
		training.setTrainerName(tr.getTrainerName());
		training.setCourseName(tr.getCourseName());
		training.setDateTime(tr.getDateTime());
		
		tsrepo.save(training);
		
		return "Training scheduled to "+ tr.getDateTime();
	}
	
	@GetMapping("/get-scheduled-trainings")
	public List<Training> getAllScheduledTrainings(){
		return tsrepo.findAll();
	}
	
	@GetMapping("/getSingleTraining/{id}")
	public Optional<Training> getTraining(@PathVariable int id) {
		return tsrepo.findById(id);
	}
	@DeleteMapping("/deleteAllTrainings")
	public String deleteAllTrainings() {
		tsrepo.deleteAll();
		return "All trainings deleted ";
	}
	
	@DeleteMapping("/delete-single-training/{name}")
	public String deleteSingleTrainig(@PathVariable String name) {
		Training training = tsrepo.findByCourseName(name);
		tsrepo.deleteByCourseName(name);
		return training.getCourseName()+" Course Deleted";
	}
	
	@DeleteMapping("/deleteTraining/{name}")
	public Training deleteTrainingByName(@PathVariable String name) {
		Training training = tsrepo.findByCourseName(name);
		tsrepo.deleteByCourseName(name);
		return training;	
	}
	@GetMapping("/getfulldetails/{id}")
	public String getFullDetails(@PathVariable int id) {

		Optional<Training> t = tsrepo.findById(id);
		Trainer trainer = trepo.findByName(t.get().getTrainerName());
		Course course = crepo.findByCoursename(t.get().getCourseName());
				
		return "Scheduled Training\n"+"Training ID : "+t.get().getId()
				
				+"\n\nTrainer : \n"
				+"\t id:"+trainer.getTrainerId()
				+ "\t name:"+trainer.getName()
				+"\t dept:"+trainer.getDept()
				
				+"\n\n Course :\n"
				+"\t course id:"+course.getCourseId()
				+"\t course name:"+course.getCoursename()
				+"\n\n Date-Time : "+t.get().getDateTime();
	}
	
	@PutMapping("/takefeedbackfor/{trainername}")
	public Trainer feedBackforTrainer(@PathVariable String trainername,@RequestParam String feedbackComment) {
		Trainer tr = trepo.findByName(trainername);
		List sample = new ArrayList<>();
		sample = tr.getFeedback();
		sample.add(feedbackComment);
		tr.setFeedback(sample);
		trepo.save(tr);
		
		RestTemplate rst = new RestTemplate();
		String url = "http://localhost:8080/email/sendFeedbackEmail-for/"+trainername;
		String mailStatus = rst.exchange(url+"?feedback={fdbck}",HttpMethod.POST,null,String.class,feedbackComment).getBody();
		System.out.println("*****> mailStatus = "+mailStatus);
		return tr;
	}
	
	@PutMapping("/change-schedule/{scheduledTrainingName}/{date}")
	public Training changeScheduleDate(@PathVariable String date,@PathVariable String scheduledTrainingName,@RequestParam String newDate) {
	
		Training training = tsrepo.findByCourseName(scheduledTrainingName);
		training.setDateTime(newDate);
		tsrepo.save(training);
		return training;
	}
	
	
	//Check : can we access another controller methods
	@Autowired
	Controller controller;
	@GetMapping("/checkthis")
	public List<Trainer> checking() {
		return this.controller.getAllTrainers();
	}
	
}
