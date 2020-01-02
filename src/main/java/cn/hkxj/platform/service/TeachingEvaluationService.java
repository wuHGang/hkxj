package cn.hkxj.platform.service;

import cn.hkxj.platform.dao.StudentDao;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.pojo.Student;
import cn.hkxj.platform.service.wechat.StudentBindService;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPagePost;
import cn.hkxj.platform.spider.newmodel.evaluation.EvaluationPost;
import cn.hkxj.platform.spider.newmodel.evaluation.searchresult.TeachingEvaluation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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

    public void evaluate(String account) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        evaluate(student);
    }

    public void evaluate(Student student) {
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

    }

    private List<EvaluationPagePost> getEvaluationPagePost(Student student) {
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

}
