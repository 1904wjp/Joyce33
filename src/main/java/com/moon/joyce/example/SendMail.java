package com.moon.joyce.example;

import com.moon.joyce.commons.utils.EmailUtils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
/**
 * @author Xing Dao Rong
 * @date 2021/9/2 17:26
 * @desc
 */
public class SendMail {
    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            EmailUtils.SendMailCode("2239122645@qq.com",5);
        }
    }


}
