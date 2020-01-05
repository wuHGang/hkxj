package cn.hkxj.platform.service;

import cn.hkxj.platform.config.wechat.WechatMpConfiguration;
import cn.hkxj.platform.config.wechat.WechatMpPlusProperties;
import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.mapper.OpenidPlusMapper;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.pojo.example.OpenidExample;
import cn.hkxj.platform.pojo.wechat.Openid;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPagePost;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPost;
import cn.hkxj.platform.spider.newmodel.evaluation.searchresult.TeachingEvaluation;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
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
    private WechatMpPlusProperties wechatMpPlusProperties;

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
                e.printStackTrace();
            }
            newUrpSpiderService.evaluate(student, post);
        });

        log.info("finish evaluate {} in {}ms", student, System.currentTimeMillis() - l);
        return getEvaluationPagePost(student).size();
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


    private List<String> getOpenIdByAccount(int account){
        OpenidExample example = new OpenidExample();
        OpenidExample.Criteria criteria = example.createCriteria();
        criteria.andAccountEqualTo(account);

        return openidPlusMapper.selectByExample(example).stream().map(Openid::getOpenid).collect(Collectors.toList());

    }

    public void sendMessage(int account, String content){
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

}
