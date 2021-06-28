package com.nisum.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "courses")
public class Course {
	@Id
	int courseId;
	String coursename;
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCoursename() {
		return coursename;
	}
	public void setCoursename(String coursename) {
		this.coursename = coursename;
	}
	@Override
	public String toString() {
		return "{\n\tcourseId : " + courseId + ",\n\tcoursename : " + coursename + "\n}";
	}
	public Course(int courseId, String coursename) {
		super();
		this.courseId = courseId;
		this.coursename = coursename;
	}

}
