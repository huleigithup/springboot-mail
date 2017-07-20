package com.itstyle.mail.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.itstyle.mail.model.Email;
import com.itstyle.mail.service.IMailService;
import com.itstyle.mail.util.MailUtil;
@Service
public class MailServiceImpl implements IMailService{
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
    public String USER_NAME;

	@Override
	public void Send(Email mail) throws Exception {
		MailUtil mailUtil = new MailUtil();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(USER_NAME);
		message.setTo(mail.getEmail());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		mailUtil.start(mailSender, message);
	}
}
