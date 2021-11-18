package com.moon.joyce.commons.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Xing Dao Rong
 * @date 2021/9/17 14:00
 * @desc 字符串工具类
 */
public class StringUtils {
    //字符串转集合
    public static List<String> StrToList(String str){
        String[] strs ={};
        if (str.contains(",")){
            strs = str.split(",");
        }else {
            strs = new String[]{str};
        }
        return  Arrays.asList(strs);
    }
    //集合是否含有某个字符串
    public static boolean listIsContainsStr(String str,List<String> list){
        for (String string : list) {
            if(org.apache.commons.lang3.StringUtils.isBlank(string)) {
                continue;
            }
            if (string.equals(str)) {
                return true;
            }
        }
        return  false;
    }
}
