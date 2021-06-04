package com.nisum.resttemplate;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nisum.controller.Controller;
import com.nisum.controller.TrainingController;
import com.nisum.email.SendTodayMails;
import com.nisum.model.Course;
import com.nisum.model.Trainer;
import com.nisum.model.Training;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateUsage {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Controller controller;
	@Autowired
	TrainingController tcontroller;
	
	
	
	
	//*********************** TRAINER RELATED*************************//
	
	@GetMapping("/listoftrainers")
	public String listTrainers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/trainers", HttpMethod.GET, entity, String.class).getBody();
	}
	@RequestMapping(value = "/addtrainer", method = RequestMethod.POST)
	public String addTrainer(@RequestBody Trainer tr) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(tr, headers);
		return restTemplate.exchange("http://localhost:8080/trainers", HttpMethod.POST, entity, String.class).getBody();
	}

	@RequestMapping(value = "/gettrainers")
	public String getTrainers() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);

		return restTemplate.exchange("http://localhost:8080/trainers", HttpMethod.GET, entity, String.class).getBody();
	}

	// different approach
	@RequestMapping(value = "/gettrainer/{name}")
	public String getTrainerbyId(@PathVariable String name) {
		String url = "http://localhost:8080/trainer/" + name;
		Trainer tr = restTemplate.getForObject(url, Trainer.class);
		return tr.toString();
	}

	@RequestMapping(value = "/updatetrainerbyid/{tid}",method = RequestMethod.PUT)
	public String updateTrainer(@PathVariable int tid) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/updatetrainer/" + tid, HttpMethod.PUT, entity, String.class)
				.getBody();
	}

	
	
	// ****************** COURSE REALTED ************************//
	
	@RequestMapping(value = "/addcourse", method = RequestMethod.POST)
	public String addCourse(@RequestBody Course cr) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Course> entity = new HttpEntity<Course>(cr, headers);
		return restTemplate.exchange("http://localhost:8080/courses", HttpMethod.POST, entity, String.class).getBody();
	}

	@RequestMapping(value = "/getcourses", method = RequestMethod.GET)
	public String getCourses() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Course> entity = new HttpEntity<Course>(headers);
		return restTemplate.exchange("http://localhost:8080/courses", HttpMethod.GET, entity, String.class).getBody();
	}
	
	@RequestMapping(value = "/deleteTrainer/{tid}")
	public String deleteTrainer(@PathVariable int tid) {
		HttpHeaders headers= new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/deletetrainer/"+tid, HttpMethod.DELETE, entity,String.class).getBody();
	}
	
	@RequestMapping(value = "/deletethecourse/{name}")
	public String deleteCourse(@PathVariable String name) {
		HttpHeaders headers= new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/deletecourse/"+name, HttpMethod.DELETE, entity,String.class).getBody();
	}

	
	
	//******************************* SCHEDULING TRAININGS RELATED*****************//
	
	@RequestMapping(value = "/scheduletraining", method = RequestMethod.POST)
	public String scheduleTraining(@RequestBody Training training) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Training> entity = new HttpEntity<Training>(training, headers);
		return restTemplate.exchange("http://localhost:8080/training-scheduler/schedule-training", HttpMethod.POST, entity, String.class).getBody();
	}
	
	@GetMapping("/list-out-scheduled-trainings")
	public String listScheduledTrainings() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/training-scheduler/get-scheduled-trainings", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping(value = "/full-details-of-training/{tid}",method = RequestMethod.GET)
	public String fullDetailsOfTraining(@PathVariable int tid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Training> entity = new HttpEntity<Training>(headers);
		return restTemplate.exchange("http://localhost:8080/training-scheduler/getfulldetails/"+ tid, HttpMethod.GET, entity, String.class)
				.getBody();
	}
	
	@RequestMapping(value = "/deleteByTrainingByCourse/{name}", method = RequestMethod.DELETE)
	public String deleteByCourseName(@PathVariable String name) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Training> entity = new HttpEntity<Training>(headers);
		return restTemplate.exchange("http://localhost:8080/training-scheduler/delete-single-training/"+name,HttpMethod.DELETE,entity,String.class).getBody();
	}
		
	
	//********************************** EMAIL FEEDBACK FOR TRAINER****************************//
	
	@RequestMapping(value = "/feedback-for-trainer/{name}", method = RequestMethod.PUT)
	public String feedbackfortrainer(@PathVariable String name, @RequestParam String feedbackComment) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/training-scheduler/takefeedbackfor/"+name+"?feedbackComment={str}",HttpMethod.PUT,entity,String.class,feedbackComment).getBody();
	}
	
	//*********************************** EMAIL REMINDER FOR TRAINER DAY BEFORE EVENT **************//
	
	@RequestMapping(value = "/send-todays-reminder-email", method = RequestMethod.POST)
	public String sendReminderEmail() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<SendTodayMails> entity = new HttpEntity<SendTodayMails>(headers);
		return restTemplate.exchange("http://localhost:8080/sendtodaymails", HttpMethod.POST, entity, String.class).getBody();
	}
	
	
}
