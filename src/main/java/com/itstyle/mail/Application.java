package com.itstyle.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
/**
 * 启动类
 * 创建者 科帮网 https://blog.52itstyle.com
 * 创建时间	2017年7月19日
 *
 */
@EnableAutoConfiguration
@ComponentScan(basePackages={"com.itstyle.main"})
@ImportResource({"classpath:spring-context-dubbo.xml","classpath:spring-context-task.xml"})
public class Application  {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(Application.class, args);
		logger.info("项目启动 ");
	}
}