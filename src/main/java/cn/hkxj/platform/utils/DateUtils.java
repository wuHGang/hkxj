package cn.hkxj.platform.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @author Yuki
 * @date 2018/11/5 23:22
 */

@Component
@Slf4j
public class DateUtils {

    private final static String term_start = "2018-08-27";

    public static Integer getCurrentYear(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        return calendar.get(Calendar.YEAR);
    }

    public static Integer getCurrentWeek(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        try{
            calendar.setTime(format.parse(term_start));
        } catch (ParseException e){
            log.error("parse string to date fail，error message{}", e.getMessage());
            throw new RuntimeException("parse string to date fail，error message" + e.getMessage());
        }
        long start = calendar.getTimeInMillis();
        long end = Calendar.getInstance().getTimeInMillis();
        return (int) Math.ceil(((end - start) / 1000 / 60 / 60 / 24 / 7)) + 1;
    }

    /**
     * 获取当天是周几
     * @return 星期几
     */
    public static Integer getCurrentDay(){
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        //星期天是1，星期一是2，星期二是3，星期三是4，星期四是5，星期五是6，星期六是7
        int currentDay = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        if(currentDay < 0){
            currentDay = 0;
        }
        return currentDay;
    }

}
