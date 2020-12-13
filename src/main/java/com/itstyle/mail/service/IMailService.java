package com.itstyle.mail.service;

import com.itstyle.mail.common.model.Email;
import com.itstyle.mail.common.model.Result;
import org.springframework.web.multipart.MultipartFile;

public interface IMailService {
    /**
     * 纯文本
     *
     * @param mail
     * @throws Exception void
     * @Author 爪哇笔记
     * @Date 2017年7月20日 更新日志
     * 2017年7月20日  爪哇笔记 首次创建
     */
    void send(Email mail) throws Exception;

    /**
     * 富文本
     *
     * @param mail
     * @throws Exception void
     * @Author 爪哇笔记
     * @Date 2017年7月20日 更新日志
     * 2017年7月20日  爪哇笔记 首次创建
     */
    void sendHtml(Email mail) throws Exception;

    /**
     * 模版发送 freemarker
     *
     * @param mail
     * @throws Exception void
     * @Author 爪哇笔记
     * @Date 2017年7月20日 更新日志
     * 2017年7月20日  爪哇笔记 首次创建
     */
    void sendFreemarker(Email mail) throws Exception;

    /**
     * 模版发送 thymeleaf(弃用、需要配合模板)
     *
     * @param mail
     * @throws Exception void
     * @Author 爪哇笔记
     * @Date 2017年7月20日 更新日志
     * 2017年7月20日  爪哇笔记 首次创建
     */
    void sendThymeleaf(Email mail) throws Exception;

    /**
     * 队列
     *
     * @param mail
     * @throws Exception void
     * @Author 科帮网
     * @Date 2017年8月4日 更新日志
     * 2017年8月4日  爪哇笔记 首次创建
     */
    void sendQueue(Email mail) throws Exception;

    /**
     * Redis 队列
     *
     * @param mail
     * @throws Exception void
     * @Author 爪哇笔记
     * @Date 2017年8月13日 更新日志
     * 2017年8月13日  科帮网 首次创建
     */
    void sendRedisQueue(Email mail) throws Exception;

    /**
     * 邮件列表
     *
     * @param mail
     * @return
     */
    Result listMail(Email mail);

    Result parseMsg(MultipartFile file);
}
