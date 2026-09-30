package com.avit.utils;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class Sendmail {
	private final JavaMailSender mailSender;

	public boolean sendMail(String to,String subject,String body) {
		boolean mailSent = false;
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body,true);
			mailSender.send(message);
			mailSent=true;
		} catch (Exception e) 
		{
			e.printStackTrace();
		}

		return mailSent;
	}
}
