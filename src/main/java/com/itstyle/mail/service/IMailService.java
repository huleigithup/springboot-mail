package com.itstyle.mail.service;

import com.itstyle.mail.model.Email;

public interface IMailService {
	 /**
	  * 纯文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  */
	 public void send(Email mail) throws Exception;
	 /**
	  * 富文本
	  * @Author  科帮网
	  * @param mail
	  * @throws Exception  void
	  * @Date	2017年7月20日
	  * 更新日志
	  * 2017年7月20日  科帮网 首次创建
	  *
	  */
	 public void sendHtml(Email mail) throws Exception;
}
