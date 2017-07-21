package com.itstyle.mail.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import com.itstyle.mail.model.Email;
import com.itstyle.mail.service.IMailService;


@SpringBootApplication
@ComponentScan(basePackages={"com.itstyle.mail"})
@ImportResource({"classpath:spring-context-dubbo.xml","classpath:spring-context-task.xml"})
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
		mail.setSubject("你个小逗比");
		mail.setContent("科帮网欢迎您");
		mail.setTemplate("welcome");
		mailService.sendFreemarker(mail);
	}
}
