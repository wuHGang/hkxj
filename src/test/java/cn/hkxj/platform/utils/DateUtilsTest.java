package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.CharUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.servlet.ServletConfig;
import java.util.List;
import java.util.Map;

/**
 * @author Yuki
 * @date 2018/11/5 23:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class DateUtilsTest {

    @Test
    public void getCurrentWeek() throws  Exception{
    }

    @Test
    public void getCurrentDay() {
        System.out.println(DateUtils.getCurrentDay());
    }

    public static void main(String[] args) {
        DateUtilsTest test = new DateUtilsTest();
        List<int[]> results = test.parseWeek("第10周");
        String week = "000000000100000000000000";
        for(int[] result : results){
            System.out.println("start = " + result[0] + "    end = " + result[1]);
            System.out.println("distinct = " + test.parseDistinct(week, result[0], result[1]));
        }
    }

    private List<int[]> parseWeek(String weekDescription) {
        String[] weeks = weekDescription.split(",");
        List<int[]> results = Lists.newArrayList();
        if (weeks.length == 1) {
            if (weekDescription.startsWith("第")) {
                String number = weekDescription.substring(1, weekDescription.length() - 1);
                int result = Integer.parseInt(number);
                results.add(new int[]{result, result});
            } else {
                results.add(parseNumber(weekDescription));
            }
            return results;
        }
        for (String str : weeks) {
            results.add(parseNumber(str));
        }
        return results;
    }

    public int[] parseNumber(String origin) {
        String[] strs = origin.split("-");
        if(strs.length == 1){
            for(int i = 0, length = strs[0].length(); i < length; i++){
                if(!CharUtils.isAsciiNumeric(strs[0].charAt(i))){
                    int result = Integer.parseInt(strs[0].substring(0, i));
                    return new int[]{result, result};
                }
            }
        }
        int one = Integer.parseInt(strs[0]), two = 0, length = strs[1].length();
        if(length == 1) {
            return new int[]{one, Integer.parseInt(strs[1])};
        }
        for (int i = 0; i < length; i++) {
            if (!CharUtils.isAsciiNumeric(strs[1].charAt(i))) {
                two = Integer.parseInt(strs[1].substring(0, i));
                break;
            }
        }
        return new int[]{one, two};
    }

    private int parseDistinct(String seq, int start, int end) {
        int count = 0, theoreticalValue = end - start + 1;
        for(int i = start - 1; i < end; i++){
            if(seq.charAt(i) == '1'){
                count++;
            }
        }
        if(theoreticalValue > count) {
            return start % 2 == 0 ? 2 : 1;
        }
        return 0;
    }
}