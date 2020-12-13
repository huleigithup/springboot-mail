package com.itstyle.mail.utils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.simplejavamail.outlookmessageparser.OutlookMessageParser;
import org.simplejavamail.outlookmessageparser.model.OutlookFileAttachment;
import org.simplejavamail.outlookmessageparser.model.OutlookMessage;
import org.simplejavamail.outlookmessageparser.model.OutlookMsgAttachment;

import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class EmailParseMsg {
    /**
     * 解析MSG邮件，可以将邮件以HTML展示。
     *
     * @param file MSG格式邮件的全路径
     * @return vo
     * @throws IOException IO异常
     */
    public static EmailPreviewVo msgParseToPreview(File file) throws IOException {

        EmailPreviewVo vo = new EmailPreviewVo();

        OutlookMessageParser msgp = new OutlookMessageParser();
        OutlookMessage msg = msgp.parseMsg(file.getAbsolutePath());

        List<FileVo> attachList = new ArrayList<>();
        for (int i = 0; i < msg.getOutlookAttachments().size(); i++) {
            /** TODO 注意：OutlookAttachment 是个接口有两个实现类，
             *  1)、OutlookFileAttachment  存在真实文件字节数据集
             *  2)、OutlookMsgAttachment 为.msg格式文件再次被递归解析
             *      目前没有好办法去获取到邮件附件为.msg格式真实文件，
             */
            // .msg格式附件暂时忽略
            if (msg.getOutlookAttachments().get(i) instanceof OutlookMsgAttachment) {
                continue;
            }
            OutlookFileAttachment attachment = (OutlookFileAttachment) msg.getOutlookAttachments().get(i);
            String attachName = attachment.getFilename();
            File attachementFile = null;
            // 创建文件 可根据自己实际情况进行使用自己的方法
            if (Utils.existSuffix(attachName)) {
                String suffix = Utils.getSuffix(attachName);
                attachementFile = Utils.createTmpFile(suffix);
            } else {
                attachementFile = Utils.createTmpFileWithName(attachName);
            }
            InputStream is = new ByteArrayInputStream(attachment.getData());
            FileUtils.copyInputStreamToFile(is, attachementFile);
            if (attachementFile != null) {
                FileVo fileVo = new FileVo();
                fileVo.setFileName(attachName);
                fileVo.setFileLength(attachementFile.length());
                fileVo.setFilePath(attachementFile.getAbsolutePath());
                attachList.add(fileVo);
            }
        }
        vo.setAttachments(attachList);
        // 内容 要处理下不然他会带有微软雅黑的样式，与原邮件样式不符
        /**
         *org.jsoup.nodes.Document
         *org.jsoup.Jsoup
         */
        Document doc = Jsoup.parse(msg.getConvertedBodyHTML());
        List<FileVo> newAttachList = new ArrayList<>();
        newAttachList.addAll(attachList);

        // 对邮件中图片进行处理
        Elements imgList = doc.select("img");
        if (imgList.size() > 0) {
            imgList.forEach(e -> {
                String src = e.attr("src");
                if (src.indexOf("cid:") < 0) {
                    return;
                }
                String imgAttach = src.substring(4);
                FileVo fileVo = null;
                for (FileVo tmp : attachList) {
                    if (tmp.getDescription().equals(imgAttach)) {
                        fileVo = tmp;
                        break;
                    }
                }
                if (fileVo == null) {
                    return;
                }
                File attach = new File(fileVo.getFilePath());
                String base64 = null;
                InputStream in = null;
                try {
                    in = new FileInputStream(attach);
                    byte[] bytes = new byte[(int) attach.length()];
                    in.read(bytes);
                    base64 = Base64.getEncoder().encodeToString(bytes);
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                if (StringUtils.isNotBlank(base64)) {
                    String srcBase64 = "data:image/png;base64," + base64;
                    e.attr("src", srcBase64);
                    if (newAttachList != null && newAttachList.size() > 0 && newAttachList.contains(fileVo)) {
                        newAttachList.remove(fileVo);
                    }
                }
            });
        }

        // 内容
        Elements bodyList = doc.select("body");
        if (bodyList.size() > 0) {
            Element bodyEle = bodyList.first();
            if (bodyEle.html().length() > 0) {
                vo.setContent(bodyEle.html());
            }
        }
        // 消息头信息   日期格式化，自己手动处理下
        vo.setSentDate(DateUtils.dateFormatString(msg.getClientSubmitTime()));
        vo.setFrom(msg.getFromEmail());
        vo.setTo(getMailUser(msg, msg.getDisplayTo()));
        vo.setCc(getMailUser(msg, msg.getDisplayCc()));
        vo.setSubject(msg.getSubject());
        return vo;
    }

    private static String getMailUser(OutlookMessage msg, String display) {
        return display;
    }


}
