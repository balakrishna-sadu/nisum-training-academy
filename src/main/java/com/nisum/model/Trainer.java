package com.nisum.model;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "trainers")
public class Trainer {
	@Id
	int trainerId;
	String name;
	String dept;
	String[] feedback;

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

	public String[] getFeedback() {
		return feedback;
	}

	public void setFeedback(String[] feedback) {
		this.feedback = feedback;
	}

	@Override
	public String toString() {
		return "Trainer [trainerId=" + trainerId + ", name=" + name + ", dept=" + dept + ", feedback="
				+ Arrays.toString(feedback) + "]";
	}

	public Trainer(int trainerId, String name, String dept, String[] feedback) {
		super();
		this.trainerId = trainerId;
		this.name = name;
		this.dept = dept;
		this.feedback = feedback;
	}

}
