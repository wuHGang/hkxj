package cn.hkxj.platform.utils;

import cn.hkxj.platform.PlatformApplication;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;
import java.io.*;

import static org.junit.Assert.*;

/**
 * @author Yuki
 * @date 2018/11/22 11:04
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class OneOffSubcriptionUtilTest {


    @Test
    public void getOneOffSubscriptionUrl() throws UnsupportedEncodingException, FileNotFoundException {
//        JsonObject jsonObject = new JsonParser().parse(OneOffSubcriptionUtil.generateDataJson("o6393wvS20PqldHQzhSngkd9SkHw", "1005")).getAsJsonObject();
//        System.out.println(jsonObject.get("touser").getAsString());
//        System.out.println(jsonObject.get("data").getAsJsonObject().get("content").getAsJsonObject().get("value").getAsString());
//        System.err.println(OneOffSubcriptionUtil.generateDataJson("o6393wvS20PqldHQzhSngkd9SkHw", "1005"));
        System.out.println(OneOffSubcriptionUtil.getOneOffSubscriptionUrl("点击获取更多", "1005"));

//        PrintWriter writer = new PrintWriter(new FileOutputStream(new File("C:\\Users\\Yuki\\Desktop\\json.txt")));
//        writer.write(OneOffSubcriptionUtil.generateDataJson("o6393wvS20PqldHQzhSngkd9SkHw"));
//        writer.flush();
//        writer.close();
    }
}