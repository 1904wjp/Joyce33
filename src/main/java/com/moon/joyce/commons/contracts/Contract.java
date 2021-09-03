package com.moon.joyce.commons.contracts;

/**
 * @author Xing Dao Rong
 * @date 2021/9/1 16:24
 * @desc 定量
 */
public class Contract {
    /**
     * 查询user条件
     */
    //登录修改查询user实体类
    public static final String USER_TYPE_LOGIN ="type:login";
    //验证用户名是否存在查询
    public static final String USER_TYPE_UNIQUE_USERNAME ="type:unique_username";
    //验证手机是否存在查询
    public static final String USER_TYPE_UNIQUE_PHONE ="type:unique_phone";
    //验证邮件是否存在查询
    public static final String USER_TYPE_UNIQUE_EMAIL ="type:unique_email";
    //用户密码
    public static final String USER_TYPE_PASSWORD ="type:password";
    //验证用户名状态激活码是否存在查询
    public static final String USER_TYPE_UNIQUE_STATUS_CODE ="type:unique_status_code";
    //用户激活
    public static final String USER_TYPE_UP_VAILD_STATUS ="type:up_vaild_status";
    //用户升级vip
    public static final String USER_TYPE_UP_VIP_STATUS ="type:up_vip_status";
    //用户未激活状态
    public static final Integer USER_TYPE_INVAILD_STATUS =0;
    //用户有效状态
    public static final Integer USER_TYPE_VAILD_STATUS =1;
    //会员用户
    public static final Integer USER_TYPE_VIP_STATUS =2;

    /**
     * 正则一般需要用到的表达式
     */
    //手机号码验证
    public static final String PHONE_FORMULA = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
    //邮件验证
    public static final String EMAIL_FORMULA = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //电话号码验证
    public static final String TELEPHONE_FORMULA = "(\\(\\d{3,4}\\)|\\d{3,4}-|\\s)?\\d{8}";
    //邮政编码验证
    public static final String POSTAL_CODE_FORMUL = "^\\d{6}$";
    //中文名验证
    public static final String CHINESE_NAME_FORMULA = "[a-zA-Z]{1,20}|[\\u4e00-\\u9fa5]{1,10}";
    //英文名验证
    public static final String ENGLISH_NAME_FORMULA = "[a-zA-Z]{1,20}";
    //网络地址验证
    public static final String INTENET_ADDRESS_FORMULA = "[a-zA-z]+://[^\\s]*";
    //ip地址验证
    public static final String IP_ADDRESS_FORMULA = "^(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])\\.(\\d{1,2}|1\\d\\d|2[0-4]\\d|25[0-5])$" ;
    //QQ验证
    public static final String QQ_FORMULA = "[1-9][0-9]{4,14}";

    /**
     * controller拼接
     */
    //重定向
    public static final String REDIRECT = "redirect:";

    /**
     * qq_email参数设置
     */
    //发送人的qq邮件
    public static final String SEND_EMAIL_CODE = "1692239985@qq.com";
    //发送人的qq密码
    public static final String SEND_EMAIL_PASSWORD = "hqlovexx1017";
    //发送人的授权码
    public static final String SEND_EMAIL_AUTH_CODE = "rhvyoanzgysdgcdc";
    //发送内容的模板
    public static final String SEND_EMAIL_TEMPLATE = "来自Joyce网站的验证,您本次的验证码为:";
    //发送验证码的范围
    public static final String SEND_EMAIL_VERIFICATION_CODE = "1234567890";
    //发送邮件主题
    public static final String SEND_EMAIL_TITLE = "来自Joyce网站的激活邮件";

    /**
     * 返回的信息
     */
    //填写错误的信息
    public static final String CHINESE_FILL_ERROR_MESSAGE = "填写有误，请填写正确信息";
    public static final String ERROR_FILL_MESSAGE = "Please fill in the correct information";
    //填写空白信息
    public static final String CHINESE_BLANK_MESSAGE= "请勿填写空白信息";
    public static final String BLANK_MESSAGE= "Do not fill in blank information";
    //查询的数据为空
    public static final String CHINESE_SLECET_BLANK_MESSAGE= "该数据不存在";
    public static final String SELECT_BLANK_MESSAGE= "The data does not exist";
    //查询的数据已存在
    public static final String CHINESE_SLECET_EXIST_MESSAGE= "该数据已存在";
    public static final String SELECT_EXIST_MESSAGE= "The data already exist";
    //查询的用户为空
    public static final String CHINESE_SLECET_BLANK_USERNAME_MESSAGE= "该用户不存在";
    public static final String SELECT_BLANK_USERNAME_MESSAGE= "The username does not exist";
    //查询的用户已存在
    public static final String CHINESE_SLECET_EXIST_USERNAME_MESSAGE= "该用户已存在";
    public static final String SELECT_EXIST_USERNAME_MESSAGE= "The username already exist";
    //未知错误
    public static final String CHINESE_ERROR_MESSAGE= "系统繁忙，请稍后再试！";
    public static final String ERROR_MESSAGE= "System busy, please try again later!";

    /**
     * Result 属性设置
     */
    //成功的编码
    public static final Integer RESULT_SUCCESS_CODE = 200;
    public static final String RESULT_SUCCESS_MSG = "操作成功";
    //错误的编码
    public static final Integer RESULT_ERROR_CODE = 500;
    public static final String RESULT_ERROR_MSG = "操作失败";
    //null的编码
    public static final Integer RESULT_NULL_CODE = 404;
    public static final String RESULT_NULL_MSG = "未找到所属数据";

}
