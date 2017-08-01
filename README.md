#spring-boot-mail

邮件发送项目，附带多种实现

##开发环境

JDK1.7、Maven、Eclipse、SpringBoot1.5.2、spring-boot-starter-mail、spring-boot-starter-thymeleaf，spring-boot-starter-freemarker

##流程图
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/190708_991f282a_87650.png "2574887637.png")

##项目结构

![springboot-mail.png](https://blog.52itstyle.com/usr/uploads/2017/07/429638365.png)


- 普通文本发送
- 富文本发送(图片、附件)
- freeMarker模版发送邮件
- thymeleaf模版发送邮件

#评测生成模版时间对比(1000次循环)


- Thymeleaf用时:2686ms
- Freemarker用时:498ms

对比测试，建议使用Freemarker模版

#20170721
- 加入DubboX对外提供发送服务
- 加入定时任务统计失败邮件定时重新发送
- 多环境配置文件实现

![输入图片说明](https://blog.52itstyle.com/usr/uploads/58ad45c0b9e21.gif "在这里输入图片标题")

作者： 小柒2012

欢迎关注： https://blog.52itstyle.com
