package cn.hkxj.platform.service;


import cn.hkxj.platform.spider.NewUrpSpider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class MakeUpService {

    public String getMakeUpService(String account, String password) {
        NewUrpSpider spider = new NewUrpSpider(account, password);
        List<Map<String, Object>> list = spider.getMakeUpGrade();
        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> map : list) {
            String grade = (String) map.get("kscj");
            if (grade == null) {
                grade = "尚无成绩";
            }
            sb.append(map.get("kcm")).append("\n成绩: ").append(grade).append("\n\n");
        }
        return sb.toString();
    }


}
