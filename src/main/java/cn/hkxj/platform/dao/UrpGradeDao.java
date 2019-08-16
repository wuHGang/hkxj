package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpGradeMapper;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.example.UrpGradeExample;
import cn.hkxj.platform.service.NewUrpSpiderService;
import cn.hkxj.platform.spider.newmodel.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.UrpGradeForSpider;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/14 22:10
 */
@Service
public class UrpGradeDao {

    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private UrpGradeMapper urpGradeMapper;
    @Resource
    private UrpGradeDetailDao urpGradeDetailDao;
    @Resource
    private UrpCourseDao urpCourseDao;
    @Resource
    private UrpExamDao urpExamDao;
    @Resource
    private MajorDao majorDao;
    @Resource
    private PlanDao planDao;

    public void insertUrpGrade(UrpGrade urpGrade){
        urpGradeMapper.insertSelective(urpGrade);
    }

    public UrpGrade insertForSpider(UrpGradeForSpider urpGradeForSpider, int examId, int account){
        UrpGrade urpGrade = urpGradeForSpider.getUrpGeneralGradeForSpider().convertToUrpGrade();
        urpGrade.setAccount(account);
        urpGrade.setExamId(examId);
        insertUrpGrade(urpGrade);
        return urpGrade;
    }

    public UrpGrade getUrpGradeByExamIdAndAccount(int examId, int account){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andExamIdEqualTo(examId);
        return urpGradeMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public List<UrpGrade> getCurrentTermAllUrpGrade(int account, List<Integer> examIds){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andAccountEqualTo(account)
                .andExamIdIn(examIds);
        return urpGradeMapper.selectByExample(example);
    }

    public GradeSearchResult saveCurrentGradeToDb(Student student, CurrentGrade currentGrade){
        GradeSearchResult result = new GradeSearchResult();
        List<UrpGradeAndUrpCourse> allContent = getUrpGradeAndUrpCourse(student, currentGrade);
        for (UrpGradeAndUrpCourse urpGradeAndUrpCourse : allContent) {
            if(urpGradeAndUrpCourse.isUpdate()){
                result.setUpdate(urpGradeAndUrpCourse.isUpdate());
                break;
            }
        }
        result.setData(allContent);
        return result;
    }

    /**
     * 检查爬虫结果中是否需要存储的同时，返回包含着所有要显示的内容的集合
     * @param student 学生实体
     * @param currentGrade 从爬虫爬取的当前学期的成绩
     * @return 所有内容
     */
    public List<UrpGradeAndUrpCourse> getUrpGradeAndUrpCourse(Student student, CurrentGrade currentGrade){
        List<UrpGradeAndUrpCourse> allContent = Lists.newArrayList();
        for(UrpGradeForSpider urpGradeForSpider : currentGrade.getList()){
            UrpGeneralGradeForSpider generalGradeForSpider = urpGradeForSpider.getUrpGeneralGradeForSpider();
            String uid = generalGradeForSpider.getId().getCourseNumber();
            int classId = student.getClasses().getId();
            int account = student.getAccount();
            //判断对应的课程是否存在，不存在就从爬虫获取后保存到数据库
            if(!urpCourseDao.ifExistCourse(uid)){
                UrpCourseForSpider urpCourseForSpider = newUrpSpiderService.getCourseFromSpider(student, uid);
                urpCourseDao.insertUrpCourse(urpCourseForSpider.convertToUrpCourse());
            }
            //判断对应的考试是否存在
            UrpExam urpExam = saveOrGetUrpExamFromDb(classId, generalGradeForSpider);
            //判断对应的成绩是否存在,并返回要显示的内容
            UrpGradeAndUrpCourse urpGradeAndUrpCourse = checkOrSaveGradeAndGradeDetail(urpExam, account, urpGradeForSpider);
            //如果更新不为空，就放到存储更新内容的集合中
            allContent.add(urpGradeAndUrpCourse);
        }
        return allContent;
    }

    /**
     * 判断成绩是否存在，如果不存在就保存成绩和相应的成绩详情
     * @param urpExam 考试实体
     * @param account   学号
     * @param urpGradeForSpider 爬虫结果
     * @return 更新内容
     */
    public UrpGradeAndUrpCourse checkOrSaveGradeAndGradeDetail(UrpExam urpExam, int account, UrpGradeForSpider urpGradeForSpider){
        UrpGrade urpGrade;
        List<UrpGradeDetail> urpGradeDetailList;
        if(!ifExistGrade(urpExam.getId(), account)){
            urpGrade = insertForSpider(urpGradeForSpider, urpExam.getId(), account);
            urpGradeDetailList = urpGradeDetailDao.insertForSpider(urpGradeForSpider, urpGrade);
            //保存更新的内容并放入updateContent这个集合中
            return getGradeUpdateContent(urpGradeForSpider, urpGrade, urpGradeDetailList);
        }
        urpGrade = getUrpGradeByExamIdAndAccount(urpExam.getId(), account);
        urpGradeDetailList = urpGradeDetailDao.getUrpGradeDetail(urpGrade.getId());
        return getGradeCommonContent(urpGradeForSpider, urpGrade, urpGradeDetailList);
    }

    /**
     * 获取更新内容
     * @param urpGradeForSpider 爬虫结果
     * @param urpGrade 成绩实体
     * @param urpGradeDetailList 成绩详情实体
     * @return 更新内容
     */
    private UrpGradeAndUrpCourse getGradeUpdateContent(UrpGradeForSpider urpGradeForSpider, UrpGrade urpGrade, List<UrpGradeDetail> urpGradeDetailList){
        UrpGradeAndUrpCourse urpGradeAndUrpCourse = generateUrpGradeAndUrpCourse(urpGradeForSpider, urpGrade, urpGradeDetailList);
        urpGradeAndUrpCourse.setUpdate(true);
        return urpGradeAndUrpCourse;
    }

    /**
     * 获取数据库中的内容
     * @param urpGradeForSpider 爬虫结果
     * @param urpGrade 成绩实体
     * @param urpGradeDetailList 成绩详情实体
     * @return 从数据库中读取的内容
     */
    private UrpGradeAndUrpCourse getGradeCommonContent(UrpGradeForSpider urpGradeForSpider, UrpGrade urpGrade, List<UrpGradeDetail> urpGradeDetailList){
        UrpGradeAndUrpCourse urpGradeAndUrpCourse = generateUrpGradeAndUrpCourse(urpGradeForSpider, urpGrade, urpGradeDetailList);
        urpGradeAndUrpCourse.setUpdate(false);
        return urpGradeAndUrpCourse;
    }

    /**
     * 生成一个UrpGradeAndUrpCourse
     * @param urpGradeForSpider 爬虫结果
     * @param urpGrade 成绩实体
     * @param urpGradeDetailList 成绩详情实体
     * @return 显示内容
     */
    private UrpGradeAndUrpCourse generateUrpGradeAndUrpCourse(UrpGradeForSpider urpGradeForSpider, UrpGrade urpGrade, List<UrpGradeDetail> urpGradeDetailList){
        UrpGradeAndUrpCourse target = new UrpGradeAndUrpCourse();
        NewGrade newGrade = new NewGrade();
        newGrade.setDetails(urpGradeDetailList);
        newGrade.setUrpGrade(urpGrade);
        target.setNewGrade(newGrade);
        target.setTerm(urpGradeForSpider.getUrpGeneralGradeForSpider().getTermForUrpGrade());
        target.setUrpCourse(urpCourseDao.getUrpCourseByUid(urpGradeForSpider.getUrpGeneralGradeForSpider().getId().getCourseNumber()));
        return target;
    }

    public boolean ifExistGrade(int examId, int account){
        UrpGradeExample example = new UrpGradeExample();
        example.createCriteria()
                .andExamIdEqualTo(examId)
                .andAccountEqualTo(account);
        return urpGradeMapper.countByExample(example) == 1;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param generalGradeForSpider 爬虫结果
     * @return 教学计划实体
     */
    private Plan saveOrGetPlanFromDb(UrpGeneralGradeForSpider generalGradeForSpider){
        Plan plan;
        if(!planDao.ifExistPlan(generalGradeForSpider.getPlanNumber())){
            plan = generalGradeForSpider.convertToPlan();
            planDao.insertPlan(plan);
        } else {
            plan = planDao.getPlanByPlanNumber(generalGradeForSpider.getPlanNumber());
        }
        return plan;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param generalGradeForSpider 爬虫结果
     * @return 专业实体
     */
    private Major saveOrGetMajorFromDb(UrpGeneralGradeForSpider generalGradeForSpider){
        Major major;
        if(!majorDao.ifExistMajor(generalGradeForSpider.getZyh())){
            major = generalGradeForSpider.convertToMajor();
            majorDao.insertMajor(major);
        } else {
            major = majorDao.getMajorByZyh(generalGradeForSpider.getZyh());
        }
        return major;
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * @param classId 班级编号
     * @param generalGradeForSpider 爬虫结果
     * @return 考试实体
     */
    private UrpExam saveOrGetUrpExamFromDb(int classId, UrpGeneralGradeForSpider generalGradeForSpider){
        String uid = generalGradeForSpider.getId().getCourseNumber();
        Term term = generalGradeForSpider.getTermForUrpGrade();
        //对教学计划相关的信息进行判断是否需要保存
        Plan plan = saveOrGetPlanFromDb(generalGradeForSpider);
        //对专业相关的信息进行判断是否需要保存
        Major major = saveOrGetMajorFromDb(generalGradeForSpider);
        UrpExam urpExam;
        if(!urpExamDao.ifExistExam(uid, classId, term)){
            urpExam = generalGradeForSpider.convertToUrpExam();
            urpExam.setPlanId(plan.getId());
            urpExam.setMajorId(major.getId());
            urpExam.setClassId(classId);
            urpExamDao.insertExam(urpExam);
        } else {
            urpExam = urpExamDao.getUrpExam(uid, term);
        }
        return urpExam;
    }
}
