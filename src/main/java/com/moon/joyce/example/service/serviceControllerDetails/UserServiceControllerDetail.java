package com.moon.joyce.example.service.serviceControllerDetails;

import com.moon.joyce.example.entity.User;
import org.springframework.stereotype.Service;

/**
 * @author: Joyce
 * @autograph: Logic is justice
 * @date: 2021/09/22-- 0:10
 * @describe:  Controller细节层：控制层比较繁琐的方法将在这里完成
 */
public interface UserServiceControllerDetail {
    /**
     * 创建发送获得发送的邮件地址
     * @param user
     * @return
     */
    String getEmailAddress(User user,String appUrl);
}
