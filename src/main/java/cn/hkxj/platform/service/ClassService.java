package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.Classes;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
@Service
public class ClassService {

    public static Classes getClassByName(String classname){
        Classes clazz = new Classes();
        String[] strs = classname.split("-");

        if(strs[1].length() > 1){
            clazz.setNum(Integer.parseInt(strs[1].substring(0, 1)));
        } else {
            clazz.setNum(Integer.parseInt(strs[1]));
        }
        int length = strs[0].length();
        for(int i = 0; i < length; i++){
            char c = strs[0].charAt(i);
            if(c >= '0' && c <= '9'){
                //year代表第几级 如16级
                clazz.setYear(Integer.parseInt(strs[0].substring(i, length)));
                //此时的targets[0]是专业名,targets[1]是班级在所在的序号
                strs[0].substring(0, i);
                return clazz;
            }
        }
        return clazz;
    }


    public static void main(String[] args) {
        getClassByName("物联网14-2");
    }
}
