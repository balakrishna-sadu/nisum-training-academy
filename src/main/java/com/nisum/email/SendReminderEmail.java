package com.nisum.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.model.Training;

@RestController
@RequestMapping(value = "/email")
public class SendReminderEmail {

	JavaMailSender mailSender;
	
	@Autowired
	SendReminderEmail(JavaMailSender javaMailSender){
		this.mailSender = javaMailSender;
	}
	
	
	@RequestMapping(value = "/send-reminder-email", method = RequestMethod.POST)
	public String sendReminderEmail(@RequestBody Training training) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("sbalakrishna@nisum.com");
		mail.setFrom("s.balu9963@gmail.com");
		mail.setSubject("Scheduled Training Reminder");
		mail.setText("Hi Balakrishna, You are receiving this mail on behalf of some of your colleague\n\n\t\t\t"
				+ "Trainer : "+training.getTrainerName().toUpperCase()
				+" This is to inform you that the training that you have been assigned :"+training.getCourseName()
				+" is on Tomorrow i.e., "+training.getDateTime()
				+"\n\n\t\t\tKindly be prepared."
				+"\n\nTHANK YOU FOR RECEIVING MAIL");

		try {
			mailSender.send(mail);
		}catch(MailException e) {
			System.out.println("Error while sending mail....."+e.getMessage());
		}
		return "reminder Mail sent for Trainer : "+training.getTrainerName();
	}
}