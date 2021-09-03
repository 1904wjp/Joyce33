package com.moon.joyce.example.controller;


import com.moon.joyce.commons.base.cotroller.BaseController;
import com.moon.joyce.commons.contracts.Contract;
import com.moon.joyce.commons.utils.FileUtils;
import com.moon.joyce.commons.utils.UUIDUtils;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.service.MailService;
import com.moon.joyce.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.ParameterResolutionDelegate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.security.PrivateKey;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Joyce
 * @since 2021-09-01
 */
@Controller
@RequestMapping("/example/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    /**
     * 注入邮件接口
     */
    @Autowired
    private MailService mailService;
    /**
     * 页面路径前缀
     */
    private String pagePrefix = "user/";

    /**
     * url的路径前缀
     */
    private String urlPrefix = "/example/user/";

    @Value("${file.upload.path}")
    private  String filePah;

    /**
     * user列表页面
     * @param user
     * @param map
     * @return
     */
    @GetMapping("/userList")
    public String getUsers(User user, ModelMap map){
        List<User> users = userService.getListUser(user);
        map.addAttribute("users",users);
        return pagePrefix+"userListPage";
    }

    /**
     * 注册页面
     * @return
     */
    @GetMapping("/regist")
    public String registPage(){
        return pagePrefix+"registPage";
    }
    /**
     * 注册结果页面
     * @return
     */
    @GetMapping("/statusResult")
    public String statusResult(){
        return pagePrefix+"statusResult";
    }
    /**
     * 登录页面
     * @return
     */
    @GetMapping("/login")
    public String loginPage(){
        return pagePrefix+"loginPage";
    }

    /**
     * user数据保存方法
     * @param user
     * @return
     */
    @PostMapping("/doSaveUser")
    public String saveUser(User user, @RequestParam("file") MultipartFile file){

        boolean result = false;
        //注册时，用户名和邮件必须唯一
        if (Objects.isNull(user.getId())){
            int userCountByUsername = userService.getUserCount(user, Contract.USER_TYPE_UNIQUE_USERNAME);
            if (userCountByUsername>0&&userCountByUsername!=-1){
                return  Contract.REDIRECT+urlPrefix+"regist";
            }
            int userCountByEmail = userService.getUserCount(user, Contract.USER_TYPE_UNIQUE_EMAIL);
            if (userCountByEmail>0&&userCountByUsername!=-1){
                return Contract.REDIRECT+urlPrefix+"regist";
            }
            //状态设置成未激活
            user.setStatus(Contract.USER_TYPE_INVAILD_STATUS);
            //随机生成状态激活码
            user.setStatusCode(UUIDUtils.getUUID());
            user.setDeleteFlag(0);
            user.setCreateTime(new Date());
            //上传图片
            String fileName = FileUtils.fileUpLoad(file);
            user.setFileUrl(fileName);
            result =  userService.saveOrUpdate(user);
            //发送邮件
            //设置html
            String context = "<a href='http://localhost:8082/example/user/checkCode?code="+user.getStatusCode()+"'>激活请点击:"+user.getStatusCode()+"</a>";
            mailService.sendHtmlMail(user.getEmail(),Contract.SEND_EMAIL_TITLE,context);
        }else {
            user.setUpdateTime(new Date());
            result = userService.saveOrUpdate(user);
        }

        if (result){
            return  Contract.REDIRECT+urlPrefix+"statusResult";
        }
            return  Contract.REDIRECT+urlPrefix+"regist";

    }

    /**
     * 邮政验证
     * @param code
     * @returnc
     */
    @RequestMapping("/checkCode")
    public String checkCode(String code){
        User user = new User();
        user.setStatusCode(code);
        System.out.println(userService.getUserCount(user,Contract.USER_TYPE_UNIQUE_STATUS_CODE));
        if (userService.getUserCount(user,Contract.USER_TYPE_UNIQUE_STATUS_CODE)!=1){
            return Contract.REDIRECT+urlPrefix+"regist";
        }
        User dbUser = userService.getUser(user,Contract.USER_TYPE_UNIQUE_STATUS_CODE);
        if (Objects.nonNull(dbUser)){
            User update= new User();
            update.setId(dbUser.getId());
            userService.updateUser(dbUser,update,Contract.USER_TYPE_UP_VAILD_STATUS);
        }
        return Contract.REDIRECT+urlPrefix+"login";
    }

    /**
     * user数据登录
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/doLogin")
    public String loginUser(String username,String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User dbUser = userService.getUser(user, Contract.USER_TYPE_LOGIN);
        if (Objects.nonNull(dbUser)){
            return Contract.REDIRECT+urlPrefix+"userList";
        }
            return Contract.REDIRECT+urlPrefix+"login";
    }

    /**
     * 删除某个user
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/delete")
    public String deleteUserById(Long id,ModelMap map){
        boolean del = userService.removeById(id);
        if (del){
            return Contract.REDIRECT+urlPrefix+"userList";
        }
        map.addAttribute("message","操作失败");
        return Contract.REDIRECT+urlPrefix+"userList";
    }

    /**
     * 编辑user的数据
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("/toUpdate")
    public String updateUser(Long id,ModelMap map){
        User dbUser = userService.getById(id);
        if (Objects.nonNull(dbUser)){
            map.addAttribute("user",dbUser);
            return pagePrefix+"updatePage";
        }
        return pagePrefix+"userList";
    }

}

