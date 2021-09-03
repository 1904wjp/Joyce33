package com.moon.joyce.commons.utils;

import com.moon.joyce.commons.contracts.Contract;
import org.apache.commons.lang3.RandomStringUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @author Xing Dao Rong
 * @date 2021/9/2 17:51
 * @desc  邮件工具类
 */
public class EmailUtils {
    /**
     * 发送验证码
     * @param recipient 邮件地址
     */
    public  static void SendMailCode(String recipient) {
        //做链接前的准备工作  也就是参数初始化
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.qq.com");//发送邮箱服务器
        properties.setProperty("mail.smtp.port", "465");//发送端口
        properties.setProperty("mail.smtp.auth", "true");//是否开启权限控制
        properties.setProperty("mail.debug", "true");//true 打印信息到控制台
        properties.setProperty("mail.transport", "smtp");//发送的协议是简单的邮件传输协议
        properties.setProperty("mail.smtp.ssl.enable", "true");
        //建立两点之间的链接
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("1692239985@qq.com", "rhvyoanzgysdgcdc");
                //这里第一个参数是发件人邮箱号码，第二个是邮箱的验证码下面解释
            }
        });

        //创建邮件对象
        Message message = new MimeMessage(session);
        try {
            //设置发件人
            message.setFrom(new InternetAddress("1692239985@qq.com"));
            //设置收件人
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));//收件人
            //设置主题
            String code = RandomStringUtils.random( 5 ,  Contract.SEND_EMAIL_VERIFICATION_CODE.toCharArray());
            message.setSubject(code);
            //设置邮件正文  第二个参数是邮件发送的类型
            String context = Contract.SEND_EMAIL_TEMPLATE+code;
            message.setContent(context, "text/html;charset=UTF-8");
            //发送一封邮件
            Transport transport = session.getTransport();
            transport.connect("1692239985@qq.com", "hqlovexx1017");
            //这里两个参数是发件人 qq账号和密码
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
        }
    }

}
