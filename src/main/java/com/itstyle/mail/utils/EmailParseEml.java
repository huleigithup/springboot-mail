package com.itstyle.mail.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

//import java.nio.file.FileVisitResult;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.nio.file.SimpleFileVisitor;
//import java.nio.file.attribute.BasicFileAttributes;

/**
 * 使用Java的mail包解析 标准的 .eml格式的邮件文件
 *
 * @author linrui
 * @date 2019/08/07
 */
public class EmailParseEml {
    public static void main(String[] args) throws Exception {
        getEmlContent();
    }

    private static Map<Object, Object> getEmlContent() throws Exception {
        Map<Object, Object> map;
        Files.walkFileTree(Paths.get("D:\\selectSQLServerEamilToXML\\filePath\\super\\"), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                try {
                    if (file.toFile().getAbsolutePath().endsWith("aa.eml")) {
                        parserFile(file.toFile().getAbsolutePath());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return super.visitFile(file, attrs);
            }
        });
        // 或者
        map = parserFile("C:\\Users\\lin\\Desktop\\18f5a04508.eml");
        System.out.println("============>" + map);
        return map;
    }

    /**
     * 解析文件
     *
     * @param emlPath 文件路径
     */
    public static Map<Object, Object> parserFile(String emlPath) throws Exception {
        Map<Object, Object> map;
        System.out.println(emlPath);
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        InputStream inMsg;
        inMsg = new FileInputStream(emlPath);
        Message msg = new MimeMessage(session, inMsg);
        map = parseEml(msg);

        return map;
    }

    private static Map<Object, Object> parseEml(Message msg) throws Exception {
        Map<Object, Object> map = new HashMap<>(10);
        // 发件人信息
        Address[] froms = msg.getFrom();
        if (froms != null) {
            InternetAddress addr = (InternetAddress) froms[0];
            System.out.println("发件人地址:" + addr.getAddress());
            map.put("fjrAddress", addr.getAddress());
            System.out.println("发件人显示名:" + addr.getPersonal());
            map.put("fjrName", addr.getPersonal());
        }
        //收件人信息
        Address[] tos = msg.getAllRecipients();
        List<String> sjrAddressList = new ArrayList<>();
        for (Address a : tos) {
            InternetAddress addr = (InternetAddress) a;
            System.out.println("====>收件人地址：" + addr.getAddress());
            sjrAddressList.add(addr.getAddress());
        }
        map.put("sjrAddressList", sjrAddressList);

        System.out.println("邮件主题:" + msg.getSubject());
        map.put("subject", msg.getSubject());
        // getContent() 是获取包裹内容, Part相当于外包装
        Object o = msg.getContent();
        if (o instanceof Multipart) {
            Multipart multipart = (Multipart) o;
            reMultipart(multipart);
        } else if (o instanceof Part) {
            Part part = (Part) o;
            rePart(part);
        } else {
            System.out.println("类型" + msg.getContentType());
            map.put("type", msg.getContentType());
            System.out.println("内容" + msg.getContent());
            map.put("content", msg.getContent());
        }
        return map;
    }


    /**
     * 解析内容
     *
     * @param part
     * @throws Exception
     */
    private static void rePart(Part part) throws Exception {
        if (part.getDisposition() != null) {
            String strFileNmae = part.getFileName();
            if (strFileNmae != null) {
                // MimeUtility.decodeText解决附件名乱码问题
                strFileNmae = MimeUtility.decodeText(strFileNmae);
                System.out.println("发现附件: " + strFileNmae);
                // 打开附件的输入流
                InputStream in = part.getInputStream();
                String strFile = "C:\\Users\\lin\\Desktop\\test\\" + strFileNmae;
                FileOutputStream out = new FileOutputStream(strFile);
                byte[] bytes = new byte[1024];
                while (in.read(bytes, 0, 1024) != -1) {
                    out.write(bytes);
                }
                in.close();
                out.close();
            }
            System.out.println("内容类型: " + MimeUtility.decodeText(part.getContentType()));
            System.out.println("附件内容:" + part.getContent());
        } else {
            if (part.getContentType().startsWith("text/plain")) {
                System.out.println("文本内容：" + part.getContent());
            } else {
                // System.out.println("HTML内容：" + part.getContent());
            }
        }
    }

    /**
     * 接卸包裹（含所有邮件内容(包裹+正文+附件)）
     *
     * @param multipart
     * @throws Exception
     */
    private static void reMultipart(Multipart multipart) throws Exception {
        System.out.println("邮件共有" + multipart.getCount() + "部分组成");
        // 依次处理各个部分
        for (int j = 0, n = multipart.getCount(); j < n; j++) {
            System.out.println("处理第" + j + "部分");
            // 解包, 取出 MultiPart的各个部分,
            Part part = multipart.getBodyPart(j);
            // 每部分可能是邮件内容,
            // 也可能是另一个小包裹(MultipPart)
            // 判断此包裹内容是不是一个小包裹, 一般这一部分是 正文 Content-Type: multipart/alternative
            if (part.getContent() instanceof Multipart) {
                Multipart p = (Multipart) part.getContent();// 转成小包裹
                // 递归迭代
                reMultipart(p);
            } else {
                rePart(part);
            }
        }
    }
}