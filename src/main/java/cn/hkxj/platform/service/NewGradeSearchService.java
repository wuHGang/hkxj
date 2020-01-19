package cn.hkxj.platform.service;

import cn.hkxj.platform.MDCThreadPool;
import cn.hkxj.platform.dao.*;
import cn.hkxj.platform.exceptions.PasswordUnCorrectException;
import cn.hkxj.platform.exceptions.UrpEvaluationException;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.ErrorCode;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.pojo.vo.GradeDetailVo;
import cn.hkxj.platform.pojo.vo.GradeResultVo;
import cn.hkxj.platform.pojo.vo.GradeVo;
import cn.hkxj.platform.pojo.vo.TermGradeVo;
import cn.hkxj.platform.spider.newmodel.grade.detail.GradeDetailSearchPost;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.grade.scheme.Scheme;
import cn.hkxj.platform.spider.newmodel.grade.scheme.SchemeGradeItem;
import cn.hkxj.platform.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.lucene.search.MultiCollectorManager;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * @author Yuki
 * @date 2019/8/1 17:05
 */
@Slf4j
@Service
public class NewGradeSearchService {

    @Resource
    private PlanDao planDao;
    @Resource
    private MajorDao majorDao;
    @Resource
    private UrpExamDao urpExamDao;
    @Resource
    private StudentDao studentDao;
    @Resource
    private UrpCourseService urpCourseService;
    @Resource
    private NewUrpSpiderService newUrpSpiderService;
    @Resource
    private GradeDao gradeDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    private static final Term currentTerm = new Term(2019, 2020, 1);

    private static DecimalFormat decimalFormat = new DecimalFormat("###################.###########");

    private static ExecutorService gradeAutoUpdatePool = new MDCThreadPool(8, 8,
            0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), r -> new Thread(r, "gradeSearch"));

    /**
     * 这个方法已经过时，冗余出来给还没更新小程序新版本的用户使用
     * 请使用新方法{@linkplain #getCurrentTermGrade}
     */
    @Deprecated
    public GradeSearchResult getCurrentGrade(Student student) {
        List<GradeVo> currentTermGrade = getCurrentTermGrade(student);
        List<UrpGradeAndUrpCourse> result = currentTermGrade.stream()
                .map(x -> new UrpGradeAndUrpCourse()
                        .setNewGrade(new NewGrade().setUrpGrade(new UrpGrade().setScore(x.getScore())))
                        .setUrpCourse(new UrpCourse().setCourseName(x.getCourse().getName())
                                .setCredit(Double.parseDouble(x.getCourse().getCredit()))
                        )
                        .setTerm(DateUtils.getCurrentSchoolTime().getTerm())

                ).collect(Collectors.toList());

        return new GradeSearchResult(result, false);
    }

    public List<GradeDetail> getCurrentTermGradeFromSpider(Student student) {
        List<UrpGeneralGradeForSpider> generalGrade = newUrpSpiderService.getCurrentGeneralGrade(student);
        generalGrade.stream().findFirst().ifPresent(x -> {
            if (!x.getId().getStudentNumber().equals(student.getAccount().toString())) {
                log.error("account {} grade error data {}", student.getAccount(), generalGrade);
            }
        });

        return generalGrade.stream().map(urpGradeForSpider -> {

            // TODO 将其它对象重新整理入库
            //对教学计划相关的信息进行判断是否需要保存
            Plan plan = urpGradeForSpider.convertToPlan();
            //对专业相关的信息进行判断是否需要保存
            Major major = urpGradeForSpider.convertToMajor();
//            UrpExam urpExam = getUrpExam(urpGradeForSpider, plan, major, student);
            Grade grade = urpGradeForSpider.convertToGrade();


            return new GradeDetail(grade, null);

        }).collect(Collectors.toList());

    }


    public List<GradeVo> getCurrentTermGrade(String account, String password) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getCurrentTermGrade(student);
    }

    /**
     * 这个方法是提供给前端使用，当抓取超时或者错误得时候会从数据库中读取数据，能保证一个有返回
     *
     * @param student 学生实体
     * @return 学生成绩
     */
    public List<GradeVo> getCurrentTermGrade(Student student) {
        CompletableFuture<List<GradeVo>> future =
                CompletableFuture.supplyAsync(() -> getCurrentTermGradeVoSync(student), gradeAutoUpdatePool);
        List<GradeVo> gradeDetailList;
        try {
            gradeDetailList = future.get(5000L, TimeUnit.MILLISECONDS);
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();

            if (cause instanceof PasswordUnCorrectException) {
                throw (PasswordUnCorrectException) cause;
            }

            List<GradeVo> gradeVoList = gradeToVo(gradeDao.getCurrentTermGradeByAccount(student.getAccount()));

            if (cause instanceof UrpEvaluationException) {
                if (gradeVoList.isEmpty()) {
                    throw (UrpEvaluationException) cause;
                }
                gradeVoList.forEach(x -> x.setErrorCode(ErrorCode.Evaluation_ERROR.getErrorCode()).setMsg(cause.getMessage()));
            } else {
                log.error("get grade error", cause);
            }

            return gradeVoList;
        } catch (InterruptedException | TimeoutException e) {
            return gradeToVo(gradeDao.getCurrentTermGradeByAccount(student.getAccount()));
        }

        return gradeDetailList;
    }


    /**
     * 这个方法主要得主要目的是为了提供给关注抓取错误，而对返回结果时间不敏感的调用方使用
     *
     * @param student 学生实体
     * @return 学生成绩
     */
    public List<GradeVo> getCurrentTermGradeVoSync(Student student) {

        return gradeToVo(getCurrentTermGradeSync(student));
    }

    public List<Grade> getCurrentTermGradeSync(Student student) {
        List<GradeDetail> gradeDetailList = getCurrentTermGradeFromSpider(student);

        List<Grade> gradeList = gradeDetailList.stream()
                .map(GradeDetail::getGrade)
                .collect(Collectors.toList());
        // 检查哪些是新的成绩数据
        List<Grade> updateList = checkUpdate(student, gradeList);
        // 新的数据插入，原有得数据更新
        saveUpdateGrade(updateList);

        if(checkFinishUpdate(updateList)){
            addCurrentFinishFetchAccount(student.getAccount().toString());
        }

        return updateList;
    }


    public boolean checkFinishUpdate(List<Grade> gradeList){
        boolean finishUpdate = true;

        for (Grade grade : gradeList) {
            if(grade.getScore() == -1){
                finishUpdate = false;
                break;
            }
        }

        return finishUpdate;
    }
    public GradeResultVo getGrade(String account, String password) {
        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));
        return getGrade(student);

    }

    /**
     * 获取所有成绩
     *
     * 为了减少不必要的抓取
     *
     * @param student 学生实体
     * @return 所有的成绩数据
     */
    public GradeResultVo getGrade(Student student) {
        GradeResultVo resultVo;

        if(isCurrentFinishFetch(student.getAccount().toString()) && isEverFinishFetch(student.getAccount().toString())){

            resultVo = gradeToResultVo(gradeDao.getGradeByAccount(student.getAccount()));
            resultVo.setMessage("完成抓取");

            return resultVo;
        }


        CompletableFuture<List<Grade>> currentFuture;
        if(isCurrentFinishFetch(student.getAccount().toString())){
            currentFuture =
                    CompletableFuture.completedFuture(gradeDao.getCurrentTermGradeByAccount(student.getAccount()));
        }else {
            currentFuture =
                    CompletableFuture.supplyAsync(() -> getCurrentTermGradeSync(student), gradeAutoUpdatePool);
        }

        CompletableFuture<List<Grade>> schemeFuture;
        if(isEverFinishFetch(student.getAccount().toString())){
            schemeFuture =
                    CompletableFuture.completedFuture(gradeDao.getEverTermGradeByAccount(student.getAccount()));
        }else {
            schemeFuture =
                    CompletableFuture.supplyAsync(() -> getSchemeGradeFromSpider(student), gradeAutoUpdatePool);
        }

        CompletableFuture<List<Grade>> completableFuture = currentFuture.thenCombine(schemeFuture,
                (x, y) -> {
                    x.addAll(y);
                    return x;
                });

        try {
            List<Grade> gradeList = completableFuture.get(5000L, TimeUnit.MILLISECONDS);
            resultVo = gradeToResultVo(gradeList);
        } catch (InterruptedException e) {
            resultVo = gradeToResultVo(gradeDao.getGradeByAccount(student.getAccount()));
            resultVo.setErrorCode(ErrorCode.SYSTEM_ERROR.getErrorCode()).setMessage(e.getMessage());
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof PasswordUnCorrectException) {
                throw (PasswordUnCorrectException) cause;
            }
            if (cause instanceof UrpEvaluationException) {
                throw (UrpEvaluationException) cause;
            } else {
                resultVo = gradeToResultVo(gradeDao.getGradeByAccount(student.getAccount()));
                resultVo.setErrorCode(ErrorCode.SYSTEM_ERROR.getErrorCode());
                resultVo.setMessage(cause.getMessage());
                log.error("get grade error", cause);
            }



        } catch (TimeoutException e) {
            resultVo = gradeToResultVo(gradeDao.getGradeByAccount(student.getAccount()));
            resultVo.setErrorCode(ErrorCode.READ_TIMEOUT.getErrorCode()).setMessage("抓取超时");
        }

        return resultVo;
    }

    public GradeResultVo gradeToResultVo(List<Grade> gradeList) {
        GradeResultVo gradeResultVo = new GradeResultVo();
        List<TermGradeVo> termGradeVoList = gradeToTermGradeList(gradeList);
        gradeResultVo.setTermGradeList(termGradeVoList);

        Double sumScore = 0.0;
        double sumCredit = 0.0;

        List<GradeVo> collect = termGradeVoList.stream().flatMap(x -> x.getGradeList().stream()).collect(Collectors.toList());

        for (GradeVo gradeVo : collect) {
            sumScore = gradeVo.getGradePoint() + sumScore;
            if(gradeVo.getCoursePropertyCode().equals("003") && StringUtils.isNotEmpty(gradeVo.getCourse().getCredit())){
                sumCredit = Double.parseDouble(gradeVo.getCourse().getCredit()) + sumCredit;
            }
        }

        double f = sumScore / collect.size();
        BigDecimal b = new BigDecimal(f);

        gradeResultVo.setGpa(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
        gradeResultVo.setOptionalCourseCredit(sumCredit);

        return gradeResultVo;

    }

    public List<TermGradeVo> gradeToTermGradeList(List<Grade> gradeList) {
        SchoolTime schoolTime = DateUtils.getCurrentSchoolTime();

        return gradeList.stream()
                .collect(Collectors.groupingBy(x -> new Term(x.getTermYear(), x.getTermOrder())))
                .entrySet().stream()
                .map(x -> new TermGradeVo()
                        .setTermYear(x.getKey().getTermYear())
                        .setTermOrder(x.getKey().getOrder())
                        .setGradeList(gradeToVo(x.getValue()))
                        .setCurrentTerm(schoolTime.getTerm().equals(x.getKey()))
                )
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public List<GradeVo> gradeToVo(List<Grade> gradeList) {
        return gradeList.stream().map(x ->
        {
            Course course = new Course();
            course.setCredit(x.getCredit().toString());
            course.setExamType(x.getExamTypeName());
            course.setExamTypeCode(x.getExamTypeCode());
            course.setCourseOrder(x.getCourseOrder());
            course.setNum(x.getCourseNumber());
            course.setName(x.getCourseName());
            course.setTermYear(x.getTermYear());
            course.setTermOrder(x.getTermOrder());
            return new GradeVo()
                    .setCourse(urpCourseService.getCourseFromCache(x.getCourseNumber(), x.getCourseOrder(),
                            x.getTermYear(), x.getTermOrder(),
                            course))
                    .setAccount(x.getAccount())
                    .setScore(x.getScore())
                    .setGradePoint(x.getGradePoint())
                    .setLevelName(x.getLevelName())
                    .setLevelPoint(x.getLevelPoint())
                    .setRank(x.getRank())
                    .setReplaceCourseNumber(x.getReplaceCourseNumber())
                    .setRemark(x.getRemark())
                    .setRetakeCourseMark(x.getRetakeCourseMark())
                    .setRetakecourseModeCode(x.getRetakecourseModeCode())
                    .setRetakeCourseModeExplain(x.getRetakeCourseModeExplain())
                    .setUnpassedReasonCode(x.getUnpassedReasonCode())
                    .setUnpassedReasonExplain(x.getUnpassedReasonExplain())
                    .setStandardPoint(x.getStandardPoint())
                    .setTermYear(x.getTermYear())
                    .setTermOrder(x.getTermOrder())
                    .setUpdate(x.isUpdate())
                    .setCoursePropertyCode(x.getCoursePropertyCode())
                    .setCoursePropertyName(x.getCoursePropertyName())
                    .setExamTime(DateUtils.localDateToDate(x.getExamTime(), DateUtils.YYYY_MM_DD_PATTERN))
                    .setExamTimeStr(x.getExamTime())
                    .setOperateTime(parseGradeOperateTime(x.getOperateTime()))
                    .setOperator(x.getOperator());
        })
                .sorted()
                .collect(Collectors.toList());

    }

    public List<Grade> getSchemeGradeFromSpider(Student student) {

        log.info("get scheme grade start");
        long l = System.currentTimeMillis();
        List<Grade> gradeList = newUrpSpiderService.getSchemeGrade(student)
                .stream()
                .map(Scheme::getCjList)
                .flatMap(Collection::stream)
                .map(SchemeGradeItem::transToGrade)
                .collect(Collectors.toList());

        for (Grade grade : gradeList) {
            gradeDao.insertSelective(grade);
        }

        log.info("get scheme grade finish in {}", System.currentTimeMillis() - l);

        addEverFinishFetchAccount(student.getAccount().toString());
        return gradeList;

    }

    private Date parseGradeOperateTime(String text) {
        if (text.length() == 12) {
            text = text + "00";
        }
        return DateUtils.localDateToDate(text, DateUtils.PATTERN_WITHOUT_SPILT);
    }

    /**
     * 返回新一个新成绩的list，旧的的成绩会被过滤
     */
    public List<Grade> checkUpdate(Student student, List<Grade> gradeList) {
        List<Grade> gradeListFromDb = gradeDao.getCurrentTermGradeByAccount(student.getAccount());
        if (CollectionUtils.isEmpty(gradeListFromDb)) {
            return gradeList;
        }


        // 这个逻辑是处理教务网同一个课程返回两个结果
        Map<String, Grade> spiderGradeMap = gradeList.stream()
                .collect(Collectors.toMap(x -> x.getCourseNumber() + x.getCourseOrder(), x -> x,
                        (oldValue, newValue) -> {
                            if (oldValue.getScore() == -1) {
                                return newValue;
                            } else {
                                return oldValue;
                            }
                        }

                ));


        Map<String, Grade> dbGradeMap = gradeListFromDb.stream()
                .collect(Collectors.toMap(x -> x.getCourseNumber() + x.getCourseOrder(), x -> x,
                        (oldValue, newValue) -> {
                            if (oldValue.getScore() == -1) {
                                return newValue;
                            } else {
                                return oldValue;
                            }
                        }

                ));

        List<Grade> resultList = spiderGradeMap.entrySet().stream()
                .map(entry -> {
                    Grade grade = dbGradeMap.remove(entry.getKey());
                    if (grade == null) {
                        entry.getValue().setUpdate(true);
                        return entry.getValue();
                    }

                    if (!Objects.equals(grade, entry.getValue())) {
                        entry.getValue().setUpdate(true);
                        entry.getValue().setId(grade.getId());
                        return entry.getValue();
                    }

                    return grade;
                }).collect(Collectors.toList());

        resultList.addAll(dbGradeMap.values());

        return resultList;
    }


    public void saveUpdateGrade(List<Grade> gradeList) {
        for (Grade grade : gradeList) {
            if (grade.getId() != null) {
                gradeDao.updateByPrimaryKeySelective(grade);
            } else {
                gradeDao.insertSelective(grade);
            }
        }


    }


    public GradeDetailVo getGradeDetail(String account, GradeDetailSearchPost gradeDetailSearchPost) {

        Student student = studentDao.selectStudentByAccount(Integer.parseInt(account));

        UrpGradeDetailForSpider detail = newUrpSpiderService.getGradeDetail(student, gradeDetailSearchPost);

        Map<String, UrpGradeDetail> detailMap = detail.convertToUrpGradeDetail().stream().collect(Collectors.toMap(UrpGradeDetail::getScoreTypeCode, x -> x));

        GradeDetailVo vo = new GradeDetailVo();
        if (detailMap.containsKey("001")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("001");
            vo.setClassGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }
        if (detailMap.containsKey("002")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("002");
            vo.setExperimentGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }
        if (detailMap.containsKey("003")) {
            UrpGradeDetail urpGradeDetail = detailMap.get("003");
            vo.setPracticeGrade(new GradeDetailVo.GradeDetailVoItem(urpGradeDetail));
        }

        return vo;

    }

    public static String gradeListToText(List<UrpGradeAndUrpCourse> studentGrades) {
        StringBuilder buffer = new StringBuilder();
        if (studentGrades.size() == 0) {
            buffer.append("尚无本学期成绩");
        } else {
            //因为查询的都是同学期的，所以取第一个元素即可
            Term term = studentGrades.get(0).getTerm();
            buffer.append("- - - - - - - - - - - - - -\n");
            buffer.append("|").append(term.getTermCode()).append("学年，").append(term.getTermName()).append("|\n");
            for (UrpGradeAndUrpCourse urpGradeAndUrpCourse : studentGrades) {
                Double grade = urpGradeAndUrpCourse.getNewGrade().getUrpGrade().getScore();
                //如果分数为空，就直接跳过当前元素
                if (Objects.isNull(grade)) continue;
                buffer.append("考试名称：").append(urpGradeAndUrpCourse.getUrpCourse().getCourseName()).append("\n")
                        .append("成绩：").append(grade == -1 ? "" : decimalFormat.format(grade)).append("   学分：")
                        .append((decimalFormat.format(urpGradeAndUrpCourse.getUrpCourse().getCredit()))).append("\n\n");
            }
            buffer.append("- - - - - - - - - - - - - -");
        }
        return buffer.toString();
    }

    public boolean isCurrentFinishFetch(String account){

        Term term = DateUtils.getCurrentSchoolTime().getTerm();
        String currentKey = RedisKeys.CURRENT_GRAD_FINISH_ACCOUNT.genKey(term.getTermYear() + term.getOrder());
        return isFinishFetch(currentKey, account);
    }

    private boolean isEverFinishFetch(String account){
        Term term = DateUtils.getCurrentSchoolTime().getTerm();
        String everKey = RedisKeys.EVER_GRAD_FINISH_ACCOUNT.genKey(term.getTermYear() + term.getOrder());
        return isFinishFetch(everKey, account);
    }

    private boolean isFinishFetch(String key, String account){

        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        return BooleanUtils.toBoolean(opsForSet.isMember(key, account));
    }

    private void addCurrentFinishFetchAccount(String account){
        Term term = DateUtils.getCurrentSchoolTime().getTerm();
        addFinishFetchAccount(RedisKeys.CURRENT_GRAD_FINISH_ACCOUNT.genKey(term.getTermYear()+term.getOrder()),
                account);
    }

    private void addEverFinishFetchAccount(String account){
        Term term = DateUtils.getCurrentSchoolTime().getTerm();
        addFinishFetchAccount(RedisKeys.EVER_GRAD_FINISH_ACCOUNT.genKey(term.getTermYear()+term.getOrder()),
                account);
    }

    private void addFinishFetchAccount(String key, String account){
        SetOperations<String, String> opsForSet = stringRedisTemplate.opsForSet();
        opsForSet.add(key, account);
    }


    private Plan getPlan(UrpGeneralGradeForSpider generalGradeForSpider) {
        return planDao.saveOrGetPlanFromDb(generalGradeForSpider.convertToPlan());
    }

    private Major getMajor(UrpGeneralGradeForSpider generalGradeForSpider) {
        return majorDao.saveOrGetMajorFromDb(generalGradeForSpider.convertToMajor(), generalGradeForSpider.getZyh());
    }

    private UrpExam getUrpExam(UrpGeneralGradeForSpider generalGradeForSpider, Plan plan, Major major, Student student) {
        UrpExam urpExamFromSpider = generalGradeForSpider.convertToUrpExam();
        urpExamFromSpider.setPlanId(plan.getId());
        urpExamFromSpider.setMajorId(major.getId());
        urpExamFromSpider.setClassId(student.getClasses());
        //判断对应的考试是否存在
        return urpExamDao.saveOrGetUrpExamFromDb(urpExamFromSpider, currentTerm);
    }

}
