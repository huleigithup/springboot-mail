#spring-boot-mail

邮件发送项目，附带多种实现

##开发环境

JDK1.7、Maven、Eclipse、SpringBoot1.5.2、spring-boot-starter-mail、spring-boot-starter-thymeleaf，spring-boot-starter-freemarker

##流程图
![输入图片说明](https://git.oschina.net/uploads/images/2017/0801/190708_991f282a_87650.png "2574887637.png")

![邮件队列](https://git.oschina.net/uploads/images/2017/0804/135111_3b197795_87650.png "邮件队列.png")

##欢迎关注

![输入图片说明](https://git.oschina.net/uploads/images/2017/0802/192404_8b5f9807_87650.jpeg "1801066129 (1).jpg")

##友情提示
由于工作原因，项目正在完善中，随时更新日志，有疑问请留言或者加群

- JAVA爱好者①:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=f316b04ba30f31190c0d8120b5c54acf245299726b4450fb6fc64753dd546bf8"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者①" title="JAVA爱好者①"></a> (满)
- JAVA爱好者②:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=b2fc105d5cf11231cd863dc829b82f50454b693ad20b892a362de5adbcc9b0b3"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者②" title="JAVA爱好者②"></a>
- JAVA爱好者③:<a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=cbee3cb06364401522ea90776a1bd83cdbbed20622b93a37158d41460537db96"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="JAVA爱好者③" title="JAVA爱好者③"></a>

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
