package com.cwp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendJobPostedNotification(String toEmail, String jobTitle) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(toEmail);
		message.setSubject("Job Posted Successfully");
		message.setText("Your job titled \"" + jobTitle +"\" has been posted.");

		mailSender.send(message);

	}

}
