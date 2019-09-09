package cn.hkxj.platform.spider.newmodel.coursetimetable;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.CharUtils;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2019/9/9 9:43
 */
@Slf4j
public class TimeAndPlaceTest {

    String weekDescription = "";

    public static void main(String[] args) {
        TimeAndPlaceTest test = new TimeAndPlaceTest();
        List<int[]> list = test.parseWeek(test.weekDescription);
        for(int[] arr : list){
            for(int i = 0; i < arr.length; i++){
                System.out.println(arr[i]);
            }
        }
    }

    private List<int[]> parseWeek(String weekDescription) {
        String[] weeks = weekDescription.split(",");
        List<int[]> results = Lists.newArrayList();
        String key = "第";
        try {
            if (weeks.length == 1) {
                if (weekDescription.startsWith(key)) {
                    String number = weekDescription.substring(1, weekDescription.length() - 1);
                    int result = Integer.parseInt(number);
                    results.add(new int[]{result, result});
                } else if (weekDescription.length() == 1 && CharUtils.isAsciiNumeric(weekDescription.charAt(0))) {
                    //这是处理3，6-12周这种情况
                    int result = Integer.parseInt(weekDescription);
                    results.add(new int[]{result, result});
                } else {
                    results.add(parseNumber(weekDescription));
                }
                return results;
            }
            for (String str : weeks) {
                results.add(parseNumber(str));
            }
        } catch (NumberFormatException e) {
            log.error ("week parse error weekDescription:{}", weekDescription);
        }
        return results;
    }

    private int[] parseNumber(String origin) {
        String[] strs = origin.split("-");
        if (strs.length == 1) {
            int one = getLastNumberIndex(strs[0]);
            int result = Integer.parseInt(strs[0].substring(0, one));
            return new int[]{result, result};
        }
        int one = Integer.parseInt(strs[0]);
        int two = getLastNumberIndex(strs[1]);
        return new int[]{one, Integer.parseInt(strs[1].substring(0, two))};
    }

    private int getLastNumberIndex(String target){
        int index = 0;
        for (int i = 0, length = target.length(); i < length; i++) {
            if (!CharUtils.isAsciiNumeric(target.charAt(i))) {
                break;
            }
            index++;
        }
        return index;
    }
}