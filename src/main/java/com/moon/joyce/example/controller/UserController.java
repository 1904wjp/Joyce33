package com.moon.joyce.example.controller;



import com.moon.joyce.commons.base.cotroller.BaseController;
import com.moon.joyce.commons.contracts.Contract;
import com.moon.joyce.commons.utils.*;
import com.moon.joyce.commons.utils.entity.Result;
import com.moon.joyce.commons.utils.entity.ReturnData;
import com.moon.joyce.example.entity.User;
import com.moon.joyce.example.entity.VerifyCode;
import com.moon.joyce.example.service.MailService;
import com.moon.joyce.example.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * <p>
 *  用户前端控制器
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
     */
    @GetMapping("/userList")
    public String getUsersPage(){
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
        return pagePrefix+"statusResultPage";
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
     * 查看页面
     * @return
     */
    @GetMapping("/queryUser/{id}")
    public String queryUserPage(@PathVariable("id") Long id,ModelMap map){
        User byId = userService.getById(id);
        map.addAttribute("user",byId);
        return pagePrefix+"queryUserPage";
    }

    /**
     * 编辑页面
     * @param id
     * @param map
     * @return
     */
    @GetMapping("/editUser/{id}")
    public String updateUser(@PathVariable Long id,ModelMap map){
        User dbUser = userService.getById(id);
        if (Objects.nonNull(dbUser)){
            map.addAttribute("user",dbUser);
            return pagePrefix+"updatePage";
        }
        return pagePrefix+"userListPage";
    }

    /**
     * user数据保存方法
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/doSaveUser")
    public Result saveUser(User user){
        //创建保存数据结果
        boolean result = false;
        //密码加密
        if (!StringUtils.isBlank(user.getPassword())){
            user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
        }
        if (Objects.nonNull(user.getId())){
            user.setUpdateTime(new Date());
            result = userService.saveOrUpdate(user);
        }
        //注册
        if (Objects.isNull(user.getId())){
            //用户是否唯一
            int userCountByUsername = userService.getUserCount(user, Contract.USER_TYPE_UNIQUE_USERNAME);
            if (userCountByUsername== Contract.RESULT_UNKNOWN_SQL_RESULT){
                return ResultUtils.error(Contract.ERROR_FILL_ERROR_CODE);
            }
            if (userCountByUsername>Contract.RESULT_NO_SQL_RESULT){
                return ResultUtils.error(Contract.ERROR_CODE,Contract.CHINESE_SLECET_EXIST_USERNAME_MESSAGE);
            }
            //邮件是否唯一
            int userCountByEmail = userService.getUserCount(user, Contract.USER_TYPE_UNIQUE_EMAIL);
            if (userCountByEmail== Contract.RESULT_UNKNOWN_SQL_RESULT){
                return ResultUtils.error(Contract.ERROR_FILL_ERROR_CODE);
            }
            if (userCountByEmail>Contract.RESULT_NO_SQL_RESULT){
                return ResultUtils.error(Contract.ERROR_CODE,Contract.CHINESE_SLECET_EXIST_MESSAGE);
            }
            //状态设置成未激活
            user.setStatus(Contract.USER_TYPE_INVAILD_STATUS);
            //随机生成状态激活码
            user.setStatusCode(UUIDUtils.getUUID());
            user.setDeleteFlag(Contract.UNDELETE_STATUS);
            user.setCreateTime(new Date());
            //发送邮件
            String emailTitle = Contract.SEND_EMAIL_TITLE;
            //设置html
            String context = "<a href="+appUrl+"/example/user/checkCode?code="+user.getStatusCode()+">"+Contract.SEND_EMAIL_TEMPLATE2+user.getStatusCode()+"</a>";
            mailService.sendHtmlMail(user.getEmail(),emailTitle,context);
            result =  userService.saveOrUpdate(user);
        }
        //结果处理
        if (result){
            User sessionUser = getSessionUser();
            if (null!=sessionUser){
               removeSessionUser();
            }
            setSession(Contract.SESSION_USER,user);
            return ResultUtils.success();
        }
            return  ResultUtils.error(Contract.ERROR_CODE,false);
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
        if (Contract.RESULT_ONE_SUCCESS_SQL_RESULT!=userService.getUserCount(user,Contract.USER_TYPE_UNIQUE_STATUS_CODE)){
            return Contract.REDIRECT+urlPrefix+"regist";
        }
        User dbUser = userService.getUser(user,Contract.USER_TYPE_UNIQUE_STATUS_CODE);
        if (Objects.nonNull(dbUser)){
            String md5Code = MD5Utils.getMD5Str(code);//激活成功，让之前生成code失效
            dbUser.setStatusCode(md5Code);
            User updateUser= new User();
            updateUser.setId(dbUser.getId());
            userService.updateUser(dbUser,updateUser,Contract.USER_TYPE_UP_VAILD_STATUS);
        }
        return Contract.REDIRECT+urlPrefix+"login";
    }

    /**
     *  登录验证
     * @param username
     * @param password
     * @return
     */
    @ResponseBody
    @RequestMapping("/doLogin")
    public Result loginUser(@RequestParam("username") String username,@RequestParam("password") String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(MD5Utils.getMD5Str(password));
        User dbUser = userService.getUser(user, Contract.USER_TYPE_LOGIN);
        if (Objects.nonNull(dbUser)){
            setSession(Contract.SESSION_USER,dbUser);
            System.out.println("fdsafdsa:"+getSessionUser());
            return ResultUtils.success();
        }
        return ResultUtils.error(Contract.ERROR_CODE,Contract.CHINESE_SLECET_BLANK_USERNAME_MESSAGE);
    }

    /**
     * 获取所有用户数据
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/userListData")
    @Transactional
    public ReturnData getUsers(User user){
        //得到总页数
        int totle = userService.getUsersCount();
        //得到user数据对象
        List<User> rows = userService.getUserList(user);
        return  new ReturnData(rows,totle);
    }

    /**
     * 删除某个user
     * @param id
     * @param map
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteUser")
    public Result deleteUser(@RequestParam String ids){
        if (StringUtils.isBlank(ids)){
            return ResultUtils.error(Contract.NULL_CODE);
        }
        List<String> list = com.moon.joyce.commons.utils.StringUtils.StrToList(ids);
        boolean del = userService.removeByIds(list);
        if (del){
            if (com.moon.joyce.commons.utils.StringUtils.listIsContainsStr(getSessionUser().getId().toString(),list)){
                return ResultUtils.error(Contract.NULL_CODE);
            }
            return ResultUtils.success();
        }
        return ResultUtils.error(Contract.ERROR_CODE);
    }

    /**
     * 编辑user的数据
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/doQueryUser")
    public Result updateUser(@RequestParam String id){
        User dbUser = userService.getById(Long.valueOf(id));
        if (Objects.nonNull(dbUser)){
            if (Contract.USER_TYPE_INVAILD_STATUS.equals(dbUser.getStatus())){
                return ResultUtils.error(Contract.INACTIVE_CODE);
            }
            if (Contract.USER_TYPE_FROZEN_STATUS.equals(dbUser.getStatus())){
                return ResultUtils.error(Contract.FROZEN_CODE);
            }
            if (Contract.USER_TYPE_VAILD_STATUS.equals(dbUser.getStatus())){
                return ResultUtils.success(dbUser);
            }
        }
        return ResultUtils.error(Contract.NULL_CODE);
    }

    /**
     * 忘记密码
     * @return
     */
    @ResponseBody
    @RequestMapping("/forgetPassword")
    public Result forgetPassword(){
        boolean result = false;
        User user = getSessionUser();
        String mailCode = EmailUtils.SendMailCode(user.getEmail(), 6);
        VerifyCode sessionVerifyCode= (VerifyCode) getSessionValue(Contract.SESSION_VERIFY_CODE);
        if (Objects.isNull(sessionVerifyCode)){
            VerifyCode verifyCode = new VerifyCode();
            verifyCode.setCreateTime(new Date());
            verifyCode.setVerifyCodeValue(mailCode);
            verifyCode.setVaildTime(6*1000l);
            setSession(Contract.SESSION_VERIFY_CODE,verifyCode);
        }else {
            result = DateUtils.dateCompare(sessionVerifyCode.getCreateTime(), new Date(), sessionVerifyCode.getVaildTime());
        }
        if (result){
           return ResultUtils.success(Contract.SEND_EMAIL_SEND_SUCCESS_MESSAGE);
        }else {
           return ResultUtils.error(Contract.ERROR_CODE,Contract.SEND_EMAIL_SEND_VAILD_TIME_MESSAGE);
        }
    }

    /**
     * 检查验证码
     * @param statusCode
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkUpdateCode")
    public Result checkUpdateCode(String code){
        User user = getSessionUser();
        VerifyCode verifyCode = (VerifyCode) getSessionValue(Contract.SESSION_VERIFY_CODE);
        if (StringUtils.isBlank(code)){
            return ResultUtils.error(Contract.ERROR_FILL_ERROR_CODE);
        }
        if (verifyCode.equals(code)){
            return ResultUtils.success();
        }
        return ResultUtils.error(Contract.ERROR_FILL_ERROR_CODE);
    }

    /**
     * 输入新密码
     * @param request
     * @param newPassword
     * @param statusCode
     * @param password
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping("/updatePassword")
    public Result updatePassword(@RequestParam("password") String password,@RequestParam("newPassword") String newPassword,@RequestParam("userId")Long id ){
      /*  User user = (User) request.getSession().getAttribute(Contract.SESSION_USER);*/
        User user = userService.getById(id);
        if (!(user.getPassword().equals(MD5Utils.getMD5Str(password)))){
            return ResultUtils.error(Contract.ERROR_FILL_ERROR_CODE);
        }
        user.setPassword(MD5Utils.getMD5Str(newPassword));
        boolean updateById = userService.updateById(user);
        if (updateById){
            userService.updateById(user);
            removeSessionUser();
            return ResultUtils.success();
        }
        return ResultUtils.error(Contract.ERROR_CODE);
    }

}

