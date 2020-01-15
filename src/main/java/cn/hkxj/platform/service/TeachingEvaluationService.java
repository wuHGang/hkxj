package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.OpenidExistException;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPagePost;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPost;
import cn.hkxj.platform.spider.newmodel.evaluation.searchresult.TeachingEvaluation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TeachingEvaluationService {
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private StudentDao studentDao;
    @Resource
    private OpenidPlusMapper openidPlusMapper;
    @Resource
    private OpenIdService openIdService;
    @Resource
    private WechatMpPlusProperties wechatMpPlusProperties;
    @Resource
    private StudentBindService studentBindService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public int evaluate(String account) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return evaluate(student);
    }

    public int evaluate(Student student) {
        List<EvaluationPagePost> postList;
        long l = System.currentTimeMillis();
        log.info("start evaluate {}", student);
        postList = getEvaluationPagePost(student);
        postList.forEach(pagePost -> {
            String token = newUrpSpiderService.getEvaluationToken(student, pagePost);
            EvaluationPost post = new EvaluationPost()
                    .setTokenValue(token)
                    .setEvaluatedPeopleNumber(pagePost.getEvaluatedPeopleNumber())
                    .setEvaluationContentNumber(pagePost.getEvaluationContentNumber())
                    .setQuestionnaireCode(pagePost.getQuestionnaireCode());
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                log.error("sleep error", e);
            }
            newUrpSpiderService.evaluate(student, post);
        });

        log.info("finish evaluate {} in {}ms", student, System.currentTimeMillis() - l);
        return getEvaluationPagePost(student).size();
    }


    public int evaluateForNotBind(Integer account, String password){
        Student student = new Student().setAccount(account).setPassword(password);
        return evaluate(student);
    }

    public void evaluateForNotBind(Integer account, String password, String appId, String openid){
        Student student = new Student().setAccount(account).setPassword(password);
        try {
            while (evaluate(student) != 0){

            }
            sendMessageToOpenId(openid, "评估已完成");
            studentBindService.studentBind(openid, account.toString(), password ,appId);
        }catch (OpenidExistException exception){
            Student studentByOpenId = openIdService.getStudentByOpenId(openid, appId);

        }catch (PasswordUnCorrectException e){
            sendMessageToOpenId(openid, "密码错误请重试");
        }


    }

    public List<EvaluationPagePost> getEvaluationPagePost(Student student) {
        TeachingEvaluation teachingEvaluation = newUrpSpiderService.searchTeachingEvaluationInfo(student);
        return teachingEvaluation.getData().stream()
                .filter(x -> "否".equals(x.getIsEvaluated()))
                .map(x -> new EvaluationPagePost()
                        .setQuestionnaireCode(x.getQuestionnaire().getQuestionnaireNumber())
                        .setQuestionnaireName(x.getQuestionnaire().getQuestionnaireName())
                        .setEvaluationContentNumber(x.getId().getEvaluationContentNumber())
                        .setEvaluatedPeople(x.getEvaluatedPeople())
                        .setEvaluatedPeopleNumber(x.getId().getEvaluatedPeople())
                ).collect(Collectors.toList());

    }

    public List<EvaluationPagePost> getEvaluationPagePost(String account){
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getEvaluationPagePost(student);
    }

    public boolean hasEvaluate(String account){
        return BooleanUtils.toBoolean(stringRedisTemplate.opsForSet().isMember(RedisKeys.FINISH_EVALUATION_SET.getName(), account));
    }

    public boolean isWaitingEvaluate(String account){
        return BooleanUtils.toBoolean(stringRedisTemplate.opsForSet().isMember(RedisKeys.WAITING_EVALUATION_SET.getName(), account));
    }

    public void addEvaluateAccount(String account){
        stringRedisTemplate.opsForSet().add(RedisKeys.WAITING_EVALUATION_SET.getName(), account);
    }

    public void addFinishEvaluateAccount(String account){
        stringRedisTemplate.opsForSet().add(RedisKeys.WAITING_EVALUATION_SET.getName(), account);
    }

    public String getEvaluationLink(){
        return studentBindService.getTextLink("https://open.weixin.qq.com/connect/oauth2/authorize?appid" +
                        "=wx541fd36e6b400648" +
                "&redirect_uri=https://platform.hackerda.com/platform/bind/evaluate&response_type=code&scope=snsapi_base&state=wx541fd36e6b400648",
                "使用一键订阅之前请先点击蓝字进行绑定"
                );

    }


    private List<String> getOpenIdByAccount(int account){
        OpenidExample example = new OpenidExample();
        OpenidExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);

        return openidPlusMapper.selectByExample(example).stream().map(Openid::getOpenid).collect(Collectors.toList());

    }

    public void sendMessageToStudent(int account, String content){
        log.info("send message {} to account {}", account, content);
        WxMpService service = WechatMpConfiguration.getMpServices().get(wechatMpPlusProperties.getAppId());
        for (String s : getOpenIdByAccount(account)) {
            WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
            wxMpKefuMessage.setContent(content);
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

    public void sendMessageToOpenId(String openid, String content){
        log.info("send message {} to openid {}", openid, content);
        WxMpService service = WechatMpConfiguration.getMpServices().get(wechatMpPlusProperties.getAppId());
        WxMpKefuMessage wxMpKefuMessage = new WxMpKefuMessage();
        wxMpKefuMessage.setContent(content);
        wxMpKefuMessage.setMsgType("text");
        wxMpKefuMessage.setToUser(openid);
        try {
            service.getKefuService().sendKefuMessage(wxMpKefuMessage);
            log.info("send openid {} info {}", openid, wxMpKefuMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }


    }

}
