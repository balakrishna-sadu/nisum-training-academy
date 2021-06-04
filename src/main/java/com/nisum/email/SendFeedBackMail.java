package com.nisum.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class SendFeedBackMail {
	
	
	JavaMailSender mailSender;
	
	@Autowired
	SendFeedBackMail(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
	
	@RequestMapping(value = "/sendFeedbackEmail-for/{trainerName}" , method = RequestMethod.POST)
	public String sendFeedBack(@RequestParam String feedback, @PathVariable String trainerName) {
		
		SimpleMailMessage mail = new SimpleMailMessage();
		
		mail.setTo("sbalakrishna@nisum.com");
		mail.setSubject("FeedBack from nisum-training-academy");
		mail.setText(" Hi Balakrishna, this mail coming because one of your trainer set this mail as their feedback receiving mail\n"
				+ "\t\t\tTrainer name : "+trainerName.toUpperCase()+"\n"
				+ "\t\t\tFeedback Comment : "+feedback+"\n\n\n\n*********** THANK YOU FOR RECEIVING APPLICATION GENERATED MAIL *****"
				);
		
		try {
			mailSender.send(mail);
		}catch(MailException e) {
			System.out.println("error while sending mail........"+e);
		}
		return "Mail send successfully";
	}

}
