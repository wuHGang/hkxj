package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.PlatformApplication;
import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPagePost;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGradeForSpider;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PlatformApplication.class)
@WebAppConfiguration
public class TeachingEvaluationServiceTest {
    @Resource
    private TeachingEvaluationService teachingEvaluationService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;


    @Test
    public void evaluate() {

        Student student = studentDao.selectStudentByAccount(2018023243);
        int evaluate = teachingEvaluationService.evaluate(student);
        System.out.println(evaluate);
    }


    @Test
    public void autoEvaluate() {
        String key = RedisKeys.WAITING_EVALUATION_SET.getName();

        long size;

        while (true) {
            String account = null;
            try {

                while ((size = stringRedisTemplate.opsForSet().size(key)) != 0) {
                    log.info("evaluate size {}", size);
                    account = stringRedisTemplate.opsForSet().pop(key);
                    while (teachingEvaluationService.evaluate(account) != 0) {

                    }
                    log.info("evaluate account {} finish", account);
                    stringRedisTemplate.opsForSet().add(RedisKeys.FINISH_EVALUATION_SET.getName(), account);

                    WxMpService service = WechatMpConfiguration.getMpServices().get(wechatMpPlusProperties.getAppId());

                    for (String s : getOpenIdByAccount(Integer.parseInt(account))) {
                        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
                        wxMpKefuMessage.setContent("久等了,评估已完成");
                        wxMpKefuMessage.setMsgType("text");
                        wxMpKefuMessage.setToUser(s);
                        try {
                            service.getKefuService().sendKefuMessage(wxMpKefuMessage);
                            log.info("send account {} info {}", account, wxMpKefuMessage);
                        } catch (WxErrorException e) {
                            e.printStackTrace();
                        }
                    }

                }

                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (account != null) {
                    if(e instanceof PasswordUnCorrectException){
                        teachingEvaluationService.sendMessage(Integer.parseInt(account), "密码错误，请重新绑定");
                    }else {
                        stringRedisTemplate.opsForSet().add(key, account);
                    }

                }

            }
        }


    }


    @Test
    public void lookUp() {
        ExecutorService gradeAutoUpdatePool = new MDCThreadPool(8, 8,
                0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeUpdate"));
        Set<String> members = stringRedisTemplate.opsForSet().members(RedisKeys.FINISH_EVALUATION_SET.getName());
        CountDownLatch latch = new CountDownLatch(members.size());
        for (String member : members) {
            gradeAutoUpdatePool.submit(() -> {
                try {
                    List<EvaluationPagePost> post = teachingEvaluationService.getEvaluationPagePost(member);
                    if (post.size() != 0) {
                        System.out.println(member + " " + post.size());
                        stringRedisTemplate.opsForSet().add(RedisKeys.WAITING_EVALUATION_SET.getName(), member);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }


            });

        }
        ;

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void lookUp2() {

        Set<String> members = stringRedisTemplate.opsForSet().members(RedisKeys.FINISH_EVALUATION_SET.getName());

        for (String member : members) {
            System.out.println(member);
            try {
                UrpGeneralGradeForSpider grade = newUrpSpiderService.getCurrentGeneralGrade(member).stream().findFirst().get();
                String preLoad = MDC.get("preLoad");
                System.out.println(member + " "+ preLoad);
            } catch (Exception e) {
                String preLoad = MDC.get("preLoad");
                System.out.println(e.getMessage() + " " + preLoad);
            }

            System.out.println("-------------------");

        }
        ;


    }

    private List<String> getOpenIdByAccount(int account) {
        OpenidExample example = new OpenidExample();
        OpenidExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);

        return openidPlusMapper.selectByExample(example).stream().map(Openid::getOpenid).collect(Collectors.toList());

    }
}