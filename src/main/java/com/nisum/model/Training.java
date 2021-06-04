package com.nisum.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scheduled_trainings")
public class Training {
	
	@Id
	int id;
	String trainerName;
	String courseName;
	String dateTime;
	public Training(int id, String trainer_name, String course_name, String date_time) {
		super();
		this.id = id;
		this.trainerName = trainer_name;
		this.courseName = course_name;
		this.dateTime = date_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	
	
	@Override
	public String toString() {
		return "Training [id=" + id + ", trainerName=" + trainerName + ", courseName=" + courseName + ", dateTime="
				+ dateTime + "]";
	}
	public Training() {
		super();
	}
}
