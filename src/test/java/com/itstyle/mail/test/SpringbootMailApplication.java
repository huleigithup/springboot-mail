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
	public void run(String... args) {
		try {
			Email mail = new Email();
			mail.setEmail(new String[]{"345849402@qq.com"});
			mail.setSubject("你个小逗比");
			mail.setContent("科帮网欢迎您");
			mail.setTemplate("welcome");
			for(int i=0;i<1000;i++){
				//测试用 小心被封
			    //mailService.sendQueue(mail);
				//mailService.sendRedisQueue(mail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
