package com.nisum.temporary;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.nisum.model.Trainer;
import com.nisum.model.Training;
import com.nisum.repo.TrainersRepo;
import com.nisum.repo.TrainingScheduleRepo;

@Component
public class CronEmails {
	
	private Logger logger = LoggerFactory.getLogger(CronEmails.class);

	@Autowired
	TrainersRepo trepo;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd:M:yyyy");
	
	Date date = new Date();
	
	String dateToday = sdf.format(date);
	
	
	
	@Test
	@Scheduled(fixedRate = 2000)
	public void printMsg() {
//		logger.info(dateToday
		System.out.println("today date : "+dateToday);
		
		List<Trainer> trainings = trepo.findAll();
		
		/*for ( Training training : trainings) {
			if(training.getDateTime().contains("))
		}*/
		
		
	}

}
