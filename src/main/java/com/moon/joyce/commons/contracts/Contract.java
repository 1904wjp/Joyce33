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
    //登录查询
    public static final String SELECT_USER_TYPE_LOGIN ="type:login";
    //验证用户名是否存在查询
    public static final String SELECT_USER_TYPE_UNIQUE_USERNAME ="type:unique_username";

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
}
