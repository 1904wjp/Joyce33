package com.moon.joyce.commons.utils;


import com.moon.joyce.commons.contracts.Contract;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Joyce
 * @autograph: Logic is justice
 * @date: 2021/09/04-- 21:26
 * @describe: 时间工具类
 */
public class DateUtils implements Serializable {
    private static final long serialVersionUID = 1345446751056258222L;
    //时间转字符串
    public static String dateForMat(String pattern, Date date){
        SimpleDateFormat dfm =  new SimpleDateFormat(pattern);
        return  dfm.format(date);
    }
    //时间的加减乘除
    public static long dateAsmd( Date date,Long number,String type) {
        if (StringUtils.isBlank(type)) {
            return -1;
        }
        SimpleDateFormat dfm = new SimpleDateFormat(Contract.DATE_TIME_SECOND);
        String dateValue = dfm.format(date);
        Date dateTime = null;
        try {
            dateTime = dfm.parse(dateValue);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (type.equals(Contract.ARITHMETIC_ADD)) {
            return dateTime.getTime() + number;
        }
        if (type.equals(Contract.ARITHMETIC_SUBTRACT)) {
            return dateTime.getTime() - number;
        }
        if (type.equals(Contract.ARITHMETIC_MULTIPLY)) {
            return dateTime.getTime() * number;
        }
        if (type.equals(Contract.ARITHMETIC_DIVIDE)) {
            if (number == 0) {
                return -1;
            }
            return dateTime.getTime() / number;
        }
        return -1;
    }
    //时间的比较
    public static boolean dateCompare( Date var1,Date var2,Long number) {
        SimpleDateFormat dfm = new SimpleDateFormat(Contract.DATE_TIME_SECOND);
        String dateValue1 = dfm.format(var1);
        String dateValue2 = dfm.format(var2);
        Long dateTime1 = null;
        Long dateTime2 = null;
        try {
            dateTime1 = dfm.parse(dateValue1).getTime();
            dateTime2 = dfm.parse(dateValue2).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
       return   (dateTime2-dateTime1)>=number;
    }

}
