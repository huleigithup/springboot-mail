package com.itstyle.mail;

import org.apache.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动类
 * 创建者 爪哇笔记 https://blog.52itstyle.vip
 * 创建时间	2017年7月19日
 * API: http://localhost:8080/swagger-ui.html
 * 
 * 启动 java -jar spring-boot-mail.jar --server.port=8080
 * linux 下 后台启动  nohup java -jar spring-boot-mail.jar --server.port=8080 &
 * 
 * 2018-10-10 更新说明：
 * 1）原当当 Dubbox 2.8.4 替换为 Dubbo 2.6.2
 * 2）原spring-context-dubbo.xml 配置 替换为 dubbo-spring-boot-starter 2.0.0
 * 3）原 zkclient 0.6 替换为 curator-recipes 4.0.1
 * 4）原 zookeeper 3.4.6 升级为 zookeeper 3.5.3
 *
 * 2019-09-08 更新说明：
 * 1）Dubbo 升级为 Apache 组织
 * 2）Dubbo 2.6.2 升级为  Apache Dubbo 2.7.3
 * 3）原 com.alibaba.spring.boot dubbo-spring-boot-starter 2.0.0 升级为  org.apache.dubbo dubbo-spring-boot-starter 2.7.3
 */
@SpringBootApplication
@EnableScheduling
//必须配置包扫描、否则Dubbo无法注册服务
@DubboComponentScan(basePackages = "com.itstyle.mail.service.impl")
public class Application  {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static final String SF_FILE_SEPARATOR = System.getProperty("file.separator");
	public static final String SF_LINE_SEPARATOR = System.getProperty("line.separator");
	public static final String SF_PATH_SEPARATOR = System.getProperty("path.separator");
	
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
		// Jsoup 解析网页
		Document document = Jsoup.parse("<!DOCTYPE html>\n" +
				"<html lang=\"en\">\n" +
				"<head>\n" +
				"    <meta charset=\"UTF-8\">\n" +
				"    <title>Title</title>\n" +
				"</head>\n" +
				"<body>\n" +
				"    <table>\n" +
				"    <p style=\"text-align:center \">功课表</p>\n" +
				"    <tr>\n" +
				"        <th>语文</th>\n" +
				"        <td>7:00-7:40</td>\n" +
				"        <td>7:50-8:30</td>\n" +
				"    </tr>\n" +
				"    <tr>\n" +
				"        <th>数学</th>\n" +
				"        <td>7:00-7:40</td>\n" +
				"        <td>7:50-8:30</td>\n" +
				"    </tr>\n" +
				"    <tr>\n" +
				"        <th>英文</th>\n" +
				"        <td>7:00-7:40</td>\n" +
				"        <td>7:50-8:30</td>\n" +
				"    </tr>\n" +
				"\n" +
				"</table>\n" +
				"</body>\n" +
				"</html>");
		Elements tr = document.getElementsByTag("tr");
		String html = tr.html();
		System.out.println("html = " + html);
		String text = tr.text();
		System.out.println("text = " + text);
		logger.info("邮件服务项目启动");

	}
}