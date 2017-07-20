package com.itstyle.mail.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.itstyle.mail.model.Email;
import com.itstyle.mail.service.IMailService;
import com.itstyle.mail.util.Constants;
import com.itstyle.mail.util.MailUtil;
@Service
public class MailServiceImpl implements IMailService{
	@Autowired
	private JavaMailSender mailSender;
	@Value("${spring.mail.username}")
    public String USER_NAME;

	@Override
	public void send(Email mail) throws Exception {
		MailUtil mailUtil = new MailUtil();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(USER_NAME);
		message.setTo(mail.getEmail());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContent());
		mailUtil.start(mailSender, message);
	}

	@Override
	public void sendHtml(Email mail) throws Exception {
		MailUtil mailUtil = new MailUtil();
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(USER_NAME);
		helper.setTo(mail.getEmail());
		helper.setSubject(mail.getSubject());
		helper.setText("<html><body><img src=\"cid:springcloud\" ></body></html>", true);
		//发送图片
		File file = ResourceUtils.getFile("classpath:static"+Constants.SF_FILE_SEPARATOR+"image"+Constants.SF_FILE_SEPARATOR+"springcloud.png");  
		helper.addInline("springcloud", file);
		//发送附件
		file = ResourceUtils.getFile("classpath:static"+Constants.SF_FILE_SEPARATOR+"file"+Constants.SF_FILE_SEPARATOR+"关注科帮网获取更多源码.zip");
		helper.addAttachment("科帮网", file);
		mailUtil.startHtml(mailSender, message);
	}
}
