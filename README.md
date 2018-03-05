# spring-boot-mail

邮件发送服务，文本，附件，模板，队列，多线程，定时任务实现多种功能！！！

[SpringBoot开发案例之整合mail发送服务](https://blog.52itstyle.com/archives/1264/)

[SpringBoot开发案例之整合mail队列篇](https://blog.52itstyle.com/archives/1385/)

## 开发环境

JDK1.7、Maven、Eclipse、SpringBoot1.5.2、spring-boot-starter-mail、spring-boot-starter-thymeleaf，spring-boot-starter-freemarker

## 流程图

### 平台架构
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/190708_991f282a_87650.png "2574887637.png")

### 进程内邮件队列
![邮件队列](https://git.oschina.net/uploads/images/2017/0804/135111_3b197795_87650.png "邮件队列.png")

## 欢迎关注

![输入图片说明](https://git.oschina.net/uploads/images/2017/0802/192404_8b5f9807_87650.jpeg "1801066129 (1).jpg")

## 友情提示
由于工作原因，项目正在完善中，随时更新日志，有疑问请留言或者加群



- JAVA爱好者:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=eba7a5d6f672c67cf942e08486e5102f0a0a6268206f873fef48a9d74f248de8"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者" title="JAVA爱好者"></a>


- JAVA爱好者①:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=f316b04ba30f31190c0d8120b5c54acf245299726b4450fb6fc64753dd546bf8"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者①" title="JAVA爱好者①"></a> (满)
- JAVA爱好者②:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=b2fc105d5cf11231cd863dc829b82f50454b693ad20b892a362de5adbcc9b0b3"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者②" title="JAVA爱好者②"></a>(满)
- JAVA爱好者③:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=cbee3cb06364401522ea90776a1bd83cdbbed20622b93a37158d41460537db96"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者③" title="JAVA爱好者③"></a>(满)

## 项目结构

```
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─itstyle
│  │  │          └─mail
│  │  │              │  Application.java
│  │  │              │  
│  │  │              ├─demo
│  │  │              │      CountDownLatchDemo.java
│  │  │              │      Ticket.java
│  │  │              │      TicketRun.java
│  │  │              │      
│  │  │              ├─model
│  │  │              │      Email.java
│  │  │              │      
│  │  │              ├─queue
│  │  │              │      ConsumeMailQueue.java
│  │  │              │      MailQueue.java
│  │  │              │      
│  │  │              ├─redis
│  │  │              │      Receiver.java
│  │  │              │      RedisConfig.java
│  │  │              │      RedisListener.java
│  │  │              │      
│  │  │              ├─service
│  │  │              │  │  IMailService.java
│  │  │              │  │  
│  │  │              │  └─impl
│  │  │              │          MailServiceImpl.java
│  │  │              │          
│  │  │              ├─task
│  │  │              │      SendMail.java
│  │  │              │      
│  │  │              └─util
│  │  │                      CommonUtil.java
│  │  │                      Constants.java
│  │  │                      MailUtil.java
│  │  │                      
│  │  ├─resources
│  │  │  │  application-dev.properties
│  │  │  │  application-prod.properties
│  │  │  │  application-test.properties
│  │  │  │  application.yml
│  │  │  │  spring-context-dubbo.xml
│  │  │  │  spring-context-task.xml
│  │  │  │  
│  │  │  └─static
│  │  │      ├─file
│  │  │      │      关注科帮网获取更多源码.zip
│  │  │      │      
│  │  │      ├─image
│  │  │      │      springcloud.png
│  │  │      │      
│  │  │      └─template
│  │  │              welcome.flt
│  │  │              welcome.html
│  │  │              
│  │  └─webapp
│  │      │  index.jsp
│  │      │  
│  │      └─WEB-INF
│  │              web.xml
│  │              
│  └─test
│      └─java
│          └─com
│              └─itstyle
│                  └─mail
│                      └─test
│                              SpringbootMailApplication.java
```


![springboot-mail.png](https://blog.52itstyle.com/usr/uploads/2017/07/429638365.png)


- 普通文本发送
- 富文本发送(图片、附件)
- freeMarker模版发送邮件
- thymeleaf模版发送邮件

# 评测生成模版时间对比(1000次循环)


- Thymeleaf用时:2686ms
- Freemarker用时:498ms

对比测试，建议使用Freemarker模版

# 20170721
- 加入DubboX对外提供发送服务
- 加入定时任务统计失败邮件定时重新发送
- 多环境配置文件实现

![输入图片说明](https://blog.52itstyle.com/usr/uploads/58ad45c0b9e21.gif "在这里输入图片标题")

作者： 小柒2012

欢迎关注： https://blog.52itstyle.com

## 测试


- 修改 application-dev.properties 中自己的邮件配置 同时配置dubbo.registry.address地址
- 执行 com.itstyle.mail.test.SpringbootMailApplication main 方法


