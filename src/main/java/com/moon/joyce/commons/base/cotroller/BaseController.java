package com.moon.joyce.commons.base.cotroller;


import com.moon.joyce.commons.constants.Constant;
import com.moon.joyce.example.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Xing Dao Rong
 * @date 2021/9/3 13:55
 * @desc 基本controller
 */
@Controller
public class BaseController {
    //app链接
    @Value("${app.url}")
    public String appUrl;
    @Value("${app.urlValue}")
    public String appUrlValue;
    //app名字
    @Value("${app.name}")
    public String appName;
    //app版本
    @Value("${app.version}")
    public String appVersion;
    //上传文件路径
    @Value("${file.upload.path}")
    public String filePath;
    //sessionMap
    public Map<String,Object> sessionMap = new HashMap<>();
    //获得session
    public HttpSession getSession(){
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
    }
    //获取在线用户
    public User getSessionUser(){
        return  (User) getSession().getAttribute(Constant.SESSION_USER);
    }
    //获取会话数据
    public Object getSessionValue(String key){
        return   getSession().getAttribute(key);
    }
    //移除当前登录用户
    public void removeSessionUser(){
        getSession().removeAttribute(Constant.SESSION_USER);
    }
    //移除会话数据
    public void removeSessionValue(String key){
         getSession().removeAttribute(key);
    }
    //set sessions属性
    public void setSession(String key,Object object){
            getSession().setAttribute(key,object);
    }
    public void setSessions(Map<String,Object> map){
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            getSession().setAttribute(entry.getKey(),entry.getValue());
        }
    }
}
