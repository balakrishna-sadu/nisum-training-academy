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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nisum.controller.Controller;
import com.nisum.controller.TrainingController;
import com.nisum.model.Course;
import com.nisum.model.Trainer;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateUsage {
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	Controller controller;
	@Autowired
	TrainingController tcontroller;
	
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
	@RequestMapping(value = "/gettrainer/{id}")
	public String getTrainerbyId(@PathVariable int id) {
		String url = "http://localhost:8080/trainer/" + id;
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

	
	@RequestMapping(value = "/scheduletraining", method = RequestMethod.POST)
	public String scheduleTraining(@RequestBody Trainer tr) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(tr, headers);
		return restTemplate.exchange("http://localhost:8080/schedule-training", HttpMethod.POST, entity, String.class).getBody();
	}
	
	@GetMapping("/list-out-scheduled-trainings")
	public String listScheduledTrainings() {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/get-scheduled-trainings", HttpMethod.GET, entity, String.class).getBody();
	}

	@RequestMapping(value = "/full-details-of-training/{tid}",method = RequestMethod.GET)
	public String fullDetailsOfTraining(@PathVariable int tid) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/getfulldetails/"+ tid, HttpMethod.GET, entity, String.class)
				.getBody();
	}

	@RequestMapping(value = "/feedback-for/{tid}",method = RequestMethod.PUT)
	public String feedbackforTrainer(@PathVariable int tid) {

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Trainer> entity = new HttpEntity<Trainer>(headers);
		return restTemplate.exchange("http://localhost:8080/takefeedbackfor/"+ tid, HttpMethod.PUT, entity, String.class)
				.getBody();
	}
}
