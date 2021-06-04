package com.nisum.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainers")
public class Trainer {
	@Id
	int trainerId;
	String name;
	String dept;
	List<String> feedback = new ArrayList<>();

	
	public Trainer() {
		
	}

	public int getTrainerId() {
		return trainerId;
	}

	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public List<String> getFeedback() {
		return feedback;
	}

	public void setFeedback(List<String> feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "{\n\ttrainerId : " + trainerId + ",\n\tname : " + name + ",\n\tdept : " + dept + ",\n\tfeedback:" + feedback.toString()+ "\n}";
	}

	public Trainer(int trainerId, String name, String dept, List<String> feedback) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.dept = dept;
		this.feedback = feedback;
	}

}
