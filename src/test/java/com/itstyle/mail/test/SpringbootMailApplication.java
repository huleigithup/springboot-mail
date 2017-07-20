package com.itstyle.mail.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.itstyle.mail.model.Email;
import com.itstyle.mail.service.IMailService;


@SpringBootApplication
@ComponentScan(basePackages={"com.itstyle.mail"})
public class SpringbootMailApplication implements CommandLineRunner {
	@Autowired
	private IMailService mailService;
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMailApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Email mail = new Email();
		mail.setEmail("2806289796@qq.com");
		mail.setSubject("你个小都比");
		mail.setContent("测试勿回");
		mail.setTemplate("welcome");
		mailService.sendThymeleaf(mail);
	}
}
