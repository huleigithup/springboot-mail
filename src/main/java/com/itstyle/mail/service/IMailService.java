package com.itstyle.mail.service;

import com.itstyle.mail.model.Email;

public interface IMailService {
	 public void Send(Email mail) throws Exception;
}
