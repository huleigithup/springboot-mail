package com.itstyle.mail.web;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.Result;
import com.itstyle.mail.service.IMailService;
import io.swagger.annotations.Api;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags ="邮件管理")
@RestController
@RequestMapping("/mail")
public class mailController {

	@Reference(version = "1.0.0")
	private IMailService mailService;

    /**
     * 简单测试
     * @param mail
     * @return
     */
	@PostMapping("send")
	public Result send(Email mail) {
		try {
			mailService.sendFreemarker(mail);
		} catch (Exception e) {
			e.printStackTrace();
			return  Result.error();
		}
		return  Result.ok();
	}

    /**
     * 列表
     * @param mail
     * @return
     */
	@PostMapping("list")
	public Result list(Email mail) {
		return mailService.listMail(mail);
	}

    /**
     * 队列测试
     * @param mail
     * @return
     */
    @PostMapping("queue")
    public Result queue(Email mail) {
        try {
            mailService.sendQueue(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  Result.ok();
    }
}
