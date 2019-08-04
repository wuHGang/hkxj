package cn.hkxj.platform.controller;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

@Controller
@EnableAutoConfiguration
@Slf4j
public class CodeController {

    private static Properties props = new Properties();
    private static Producer kaptchaProducer;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    static {
        props.put(Constants.KAPTCHA_IMAGE_WIDTH, "180");
        props.put(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        props.put(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "red");
        props.put(Constants.KAPTCHA_SESSION_KEY, "kaptcha");
        props.put(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, "宋体,楷体,微软雅黑");
        Config config = new Config(props);
        kaptchaProducer = config.getProducerImpl();
    }

    @RequestMapping("/kaptcha")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        log.info("request");
        HttpSession session = request.getSession();
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码

        String capText = kaptchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        //向客户端写出
        BufferedImage bi = kaptchaProducer.createImage(capText);
        ByteArrayOutputStream wrapperOutputStream = new ByteArrayOutputStream();

        ImageIO.write(bi, "jpg", wrapperOutputStream);

        BASE64Encoder encoder = new BASE64Encoder();
        BASE64Decoder decoder = new BASE64Decoder();

        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();

        String encode = encoder.encode(wrapperOutputStream.toByteArray());
        ServletOutputStream out = response.getOutputStream();

        opsForValue.set("test", encode);
//        String test = opsForValue.get("test");
        try {
            out.write(decoder.decodeBuffer(encode));
            out.flush();
        } finally {
            out.close();
        }
    }

    @RequestMapping("/code")
    @ResponseBody
    public String getKaptchaCode(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return session.getAttribute(Constants.KAPTCHA_SESSION_KEY).toString();
    }

    public static void main(String[] args) {
        SpringApplication.run(CodeController.class, args);
    }
}
