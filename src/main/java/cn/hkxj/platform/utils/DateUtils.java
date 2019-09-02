package cn.hkxj.platform.utils;

import cn.hkxj.platform.pojo.SchoolTime;
import cn.hkxj.platform.pojo.Term;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Yuki
 * @date 2018/11/5 23:22
 */

@Component
@Slf4j
public class DateUtils {

    private final static String term_start = "2019-08-26";

    public final static String YYYY_MM_DD_PATTERN = "yyyyMMdd";

    public final static String YYYY_MM_PATTERN = "yyyyMM";

    public final static String HH_MM_SS_PATTERN = "hh:mm:ss";

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

    /**
     * 获取当前时间戳
     *
     * @param localDateTime 时间
     *
     * @return 时间戳；或者null
     */
    public static Long getTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    public static String getTimeOfPattern(LocalDateTime localDateTime,String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(localDateTime);
    }


    public static LocalDateTime timestampToLocalDateTime(Long timestamp) {
        if (timestamp == null) {
            return null;
        }

        Instant instant = Instant.ofEpochSecond(timestamp);

        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    public static LocalDateTime string2LocalDateTime(String time,String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern)).atStartOfDay();
    }

   public static SchoolTime getCurrentSchoolTime(){
        SchoolTime schoolTime = new SchoolTime();
        schoolTime.setDay(getCurrentDay());
        schoolTime.setWeek(getCurrentWeek());
        schoolTime.setTerm(new Term(2019, 2020, 1));
        return schoolTime;
   }

   public static byte getDistinct(){
        return (byte) (getCurrentWeek() % 2 == 0 ? 2 : 1);
   }

   public static byte getContraryDistinct(){
       return (byte) (getCurrentWeek() % 2 == 0 ? 1 : 2);
   }
}
