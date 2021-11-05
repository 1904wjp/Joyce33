package com.moon.joyce.commons.utils;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author Xing Dao Rong
 * @date 2021/9/3 14:20
 * @desc uuid工具类
 */
public class UUIDUtils implements Serializable {
    private static final long serialVersionUID = 8384493443192564756L;

    /**
     * 获得一个纯数字字母的字符串
     * @return
     */
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

    /**
     * 获得一个长度为len的纯数字字母的字符串
     * @param len
     * @return
     */
    public static String getUUID(int len){
        return UUID.randomUUID().toString().replace("-","").length()>=len ? UUID.randomUUID().toString().replace("-","").substring(len):UUID.randomUUID().toString().replace("-","");
    }

}