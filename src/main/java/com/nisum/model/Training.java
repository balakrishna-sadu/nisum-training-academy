package com.nisum.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scheduled_trainings")
public class Training {
	
	@Id
	int id;
	String trainer_name;
	String course_name;
	String date_time;
	public Training(int id, String trainer_name, String course_name, String date_time) {
		super();
		this.id = id;
		this.trainer_name = trainer_name;
		this.course_name = course_name;
		this.date_time = date_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTrainer_name() {
		return trainer_name;
	}
	public void setTrainer_name(String trainer_name) {
		this.trainer_name = trainer_name;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getDate_time() {
		return date_time;
	}
	public void setDate_time(String date_time) {
		this.date_time = date_time;
	}
	@Override
	public String toString() {
		return "Training [id=" + id + ", trainer_name=" + trainer_name + ", course_name=" + course_name + ", date_time="
				+ date_time + "]";
	}
	
	public Training() {
		super();
	}
}
