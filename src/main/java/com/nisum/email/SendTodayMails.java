package com.nisum.email;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nisum.model.Training;
import com.nisum.repo.TrainingScheduleRepo;

@RestController
public class SendTodayMails {

	@Autowired
	TrainingScheduleRepo tsrepo;

	@Autowired
	RestTemplate restTemplate;

	@RequestMapping("/sendtodaymails")
	public String sendTodayMails() {

		List<Training> trainings = tsrepo.findAll();

		for (Training training : trainings) {

			String dateTime = training.getDateTime();
			Boolean result = SendTodayMails.calculateDate(dateTime);

			if (result) {

				HttpHeaders header = new HttpHeaders();
				header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
				HttpEntity<Training> entity = new HttpEntity<Training>(training, header);
				String msg = restTemplate.exchange("http://localhost:8080/email/send-reminder-email", HttpMethod.POST,
						entity, String.class).getBody();
				System.out.println(msg);
			}
		}
		return "Check Console ";
	}

	private static String[] getDevidedDate(String dateTime) {

		String[] dateSplit = dateTime.split(" ");
		// System.out.print("Date : " + dateTime + " len:" + dateSplit.length);
		String[] date = dateSplit[0].split("-");
		// System.out.println("divided date : " + date.length);

		return date;
	}

	public static Boolean calculateDate(String dateTime) {

//		System.out.println("\n\n****************\n Training date \n");
		String[] date = SendTodayMails.getDevidedDate(dateTime);

		String day = date[0];
		String month = date[1];
		String year = date[2];

		int d1 = Integer.valueOf(day);
		int y1 = Integer.valueOf(year);
		int m1 = MonthNumber.getMonthNumber(month);

//		System.out.println("d1="+d1+" m1="+m1+" y1="+y1);

//		System.out.println("\n\n****************\n Current date\n");
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-M-yyyy HH:mm a");
		String formattedDate = myDateObj.format(myFormatObj);

		String[] anotherDate = SendTodayMails.getDevidedDate(formattedDate);

		String day2 = anotherDate[0];
		String month2 = anotherDate[1];
		String year2 = anotherDate[2];

		int d2 = Integer.valueOf(day2);
		int y2 = Integer.valueOf(year2);
		int m2 = Integer.valueOf(month2);

//		System.out.println("d2="+d2+" m2="+m2+" y2="+y2);

		int diffDay = d1 - d2;
		int diffMonth = m1 - m2;
		int diffYear = y1 - y2;

		System.out.println("\n\n****************\n Difference date\n Days=" + diffDay + " Months=" + diffMonth
				+ " Years=" + diffYear);

		Boolean decision = SendTodayMails.takeDecision(diffDay, diffMonth, diffYear);

		System.out.println("_______________________________________final decision : " + decision);

		return decision;
	}

	private static Boolean takeDecision(int diffDay, int diffMonth, int diffYear) {

		if (diffYear == 0 && diffMonth == 0 && diffDay == 1) {
			System.out.println("Yeah good to send mails today");
			return true;
		}
		return false;
	}
}
