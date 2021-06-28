package com.nisum.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.nisum.model.Course;
import com.nisum.model.Trainer;
import com.nisum.model.Training;
import com.nisum.repo.CoursesRepo;
import com.nisum.repo.TrainersRepo;
import com.nisum.repo.TrainingScheduleRepo;

@SpringBootTest
public class NisumAcademyTest {

	@Autowired
	TrainersRepo trepo;

	@Autowired
	CoursesRepo crepo;

	@Autowired
	TrainingScheduleRepo tsrepo;

	// **************** Trainer Class ************************//

	@Disabled
	@Test																		 // success
	public void testNoOfTrainers() {
		List<Trainer> trainers = trepo.findAll();
		assertTrue(trainers.size() > 0);
		assertNotNull(trainers.get(0));
	}

	@Disabled
	@Test																		 // failure
	public void testTrainerName() {

		Trainer trainerNagendra = trepo.findByName("nagendra");
		assertNotEquals("nagendra", trainerNagendra.getName()); // these are equal but we are checking for not equal
	}

	@Disabled
	@Test 																		// success
	@Rollback(true)
	public void testAddTrainer() {

		Trainer t = new Trainer();
		t.setTrainerId(6);
		t.setName("rakesh");
		t.setDept("FullStack");
		List<String> feedback = new ArrayList<String>();
		feedback.add("knowledgeable");
		t.setFeedback(feedback);
		Trainer testTrainer = trepo.save(t);
		assertTrue(testTrainer.getName().equals(t.getName()));
		assertSame(testTrainer, t);
	}

	// *************** Course Class ****************************//

	@Disabled
	@Test																		 // success
	public void testCourseName() {

		Course courseJava8 = crepo.findByCoursename("Java-8");
		assertTrue(courseJava8.getCoursename().equalsIgnoreCase("java-8"));
		assertTrue(courseJava8.getCoursename().endsWith("8"));
	}

	@Disabled
	@Test 																		// failure
	public void testCourseId() {
		Optional<Course> course = crepo.findById(208);
		assertEquals("SQL", course.get().getCoursename()); // coure name was Spring-Cloud but we checking with SQL
	}
	@Disabled
	@Rollback(true)
	@Test
	public void addCourse() {
		Course course = new Course(208, "Spring-Cloud");
		Course addedCourse = crepo.save(course);
		assertNotNull(addedCourse);
		assertSame(course, addedCourse);
	}

	// *************** Training Schedule ***********************//
	@Disabled
	@Test 																		// success
	public void testTrainingSchedule() {

		Training training = tsrepo.findByCourseName("Springboot");
		System.out.println(training);
		assertFalse(training.getTrainerName().equals("Balu"));
	}

	@Disabled
	@Test																		 // failure
	public void testNameOfTheTrainer() {
		Optional<Training> training = tsrepo.findById(1);
		assertNotNull(training);
		assertEquals("janipasha", training.get().getTrainerName()); // trainer name was nagendra but we checking with
																	// janipasha
	}

	@Disabled
	@Test 																		// success
	public void testDate() {
		Optional<Training> training = tsrepo.findById(2);
		System.out.println(training.get());
		String date = training.get().getDateTime();
		assertTrue(date.contains("12-June-2021"));
	}

	@Disabled
	@Test																		//success
	public void testTrainingTodayOrNot() {
		List<Training> tr = tsrepo.findAll();
		
		Boolean testResult = false;
		
		for(Training training:tr) {
			
			System.out.println(training.getDateTime());
			if(training.getDateTime().contains("17-June-2021")) {
				assertTrue(training.getDateTime().startsWith("17-June-2021"));
				testResult=true;
				break;
			}
		}
		
		if(testResult == false) {
			System.out.println("No trainings found on specified Date !!");
		}
		assertTrue(testResult);
	}
}
