package com.nisum.email.scheduling;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.nisum.model.Training;
import com.nisum.repo.TrainingScheduleRepo;

@Component
public class SpringSchedulingEmails {

	@Autowired
	TrainingScheduleRepo tsrepo;

	@Autowired
	RestTemplate restTemplate;

	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMMM-yyyy");
	Date date = new Date();
	String todayDate = dateFormat.format(date);

	// daily reminder
	
	@Scheduled(cron = "0 6 15 ? * MON-FRI") // sending mails daily from Monday to Friday at our specified time
	public void sendTodayEmailss() {

		System.out.println("Today Converted DATE : " + todayDate);

		List<Training> trainings = tsrepo.findByStatus("NOT STARTED");

		System.out.println("Length of not started training list : " + trainings.size());

		for (Training training : trainings) {
			System.out.println("checking training : " + training.getId() + " dateTime : " + training.getDateTime());

			if (training.getDateTime().contains(todayDate)) {
				System.out.println("Yes today "+training.getCourseName()+" training is there !!");
				sendDailyReminderEmail(training);
			}
		}
	}

	// weekly reminder
	
	@Scheduled(cron = "0 30 9 ? * SUN") // sending mails on sunday morning 9:30 AM
	public void weeklyReminder() {

		List<Training> trainings = tsrepo.findAll();

		for (Training training : trainings) {

			sendWeeklyReminderEmail(training);
		}
	}

	private void sendWeeklyReminderEmail(Training training) {
		String url = "http://localhost:8080/email/send-weekly-reminder-email";
		sendEmailsUsingRest(training, url);
		System.out.println("sending weekly reminder email for training ID : " + training.getId() + " courseName: "
				+ training.getCourseName());
	}

	private void sendDailyReminderEmail(Training training) {
		String url = "http://localhost:8080/email/send-reminder-email";
		sendEmailsUsingRest(training, url);
		System.out.println("sending daily reminder email for training ID : " + training.getId() + " courseName : "
				+ training.getCourseName());
	}

	private void sendEmailsUsingRest(Training training, String url) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<Training> entity = new HttpEntity<Training>(training, headers);
		String result = restTemplate.exchange(url, HttpMethod.POST, entity, String.class).getBody();
		System.out.println("mail send for " + result);
	}
}