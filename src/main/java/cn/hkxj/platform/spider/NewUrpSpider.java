package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.*;
import cn.hkxj.platform.pojo.constant.RedisKeys;
import cn.hkxj.platform.spider.model.UrpStudentInfo;
import cn.hkxj.platform.spider.model.VerifyCode;
import cn.hkxj.platform.spider.newmodel.CourseRelativeInfo;
import cn.hkxj.platform.spider.newmodel.course.UrpCourseForSpider;
import cn.hkxj.platform.spider.newmodel.coursetimetable.UrpCourseTimeTableForSpider;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPojo;
import cn.hkxj.platform.spider.newmodel.emptyroom.EmptyRoomPost;
import cn.hkxj.platform.spider.newmodel.examtime.UrpExamTime;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.detail.UrpGradeDetailForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGradeForSpider;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassCourseSearchResult;
import cn.hkxj.platform.spider.newmodel.searchcourse.ClassInfoSearchResult;
import cn.hkxj.platform.utils.ApplicationUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.MDC;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author junrong.chen
 */
@Slf4j
public class NewUrpSpider {
    private static final String ROOT = "http://xsurp.usth.edu.cn";
    /**
     * 验证码
     */
    private static final String CAPTCHA = ROOT + "/img/captcha.jpg";
    /**
     * 登录校验
     */
    private static final String CHECK = ROOT + "/j_spring_security_check";
    /**
     * 仅做header refer使用
     */
    private static final String LOGIN = ROOT + "/getStudentInfo";
    /**
     * 学生基本信息
     */
    private static final String STUDENT_INFO = ROOT + "/student/rollManagement/rollInfo/index";
    /**
     * 当前学期成绩
     */
    private static final String CURRENT_TERM_GRADE = ROOT + "/student/integratedQuery/scoreQuery/thisTermScores/data";
    /**
     * 成绩详细信息  平时分，排行 etc
     */
    private static final String CURRENT_TERM_GRADE_DETAIL = ROOT + "/student/integratedQuery/scoreQuery/coursePropertyScores/serchScoreDetail";
    private static final String COURSE_DETAIL = ROOT + "/student/integratedQuery/course/courseSchdule/detail";
    private static final String EXAM_TIME = ROOT + "/student/examinationManagement/examPlan/index";
    private static final String COURSE_TIME_TABLE = ROOT + "/student/courseSelect/thisSemesterCurriculum/ajaxStudentSchedule/callback";
    private static final String MAKE_UP_GRADE = ROOT + "/student/examinationManagement/examGrade/search";
    /**
     * 空教室查询
     */
    private static final String EMPTY_ROOM = ROOT + "/student/teachingResources/freeClassroomQuery/search";
    /**
     * 班级信息查询
     */
    private static final String CLASS_INFO = ROOT + "/student/teachingResources/classCurriculum/search";
    /**
     * 根据班级号和学期号查询班级课程信息地址
     */
    private static final String SEARCH_COURSE_INFO = ROOT + "/student/teachingResources/classCurriculum" +
            "/searchCurriculumInfo/callback";
    private static StringRedisTemplate stringRedisTemplate;
    private static final TypeReference<UrpGradeDetailForSpider> gradeDetailTypeReference
            = new TypeReference<UrpGradeDetailForSpider>() {
    };
    private static final TypeReference<List<UrpCourseForSpider>> courseTypeReference
            = new TypeReference<List<UrpCourseForSpider>>() {
    };
    private static final TypeReference<List<ClassInfoSearchResult>> classInfoReference
            = new TypeReference<List<ClassInfoSearchResult>>() {
    };
    private static final TypeReference<List<List<ClassCourseSearchResult>>> classCourseSearchResultReference
            = new TypeReference<List<List<ClassCourseSearchResult>>>() {
    };
    private static final Splitter SPACE_SPLITTER = Splitter.on(" ").omitEmptyStrings().trimResults();

    private static final UrpCookieJar COOKIE_JAR = new UrpCookieJar();

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .cookieJar(COOKIE_JAR)
            .retryOnConnectionFailure(true)
            .connectTimeout(500L, TimeUnit.MILLISECONDS)
            .addInterceptor(new RetryInterceptor(10))
            .followRedirects(false)
            .build();

    private final static Headers HEADERS = new Headers.Builder()
            .add("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
            .add("Host", "xsurp.usth.edu.cn")
            .add("Connection", "keep-alive")
            .add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.100 Safari/537.36")
            .add("Upgrade-Insecure-Requests", "1")
            .add("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8")
            .add("Cache-Control", "max-age=0")
            .add("Referer", ": http://xsurp.usth.edu.cn/login")
            .build();

    private String account;
    private String password;

    private static ArrayBlockingQueue<PreLoadCaptcha> queue = new ArrayBlockingQueue<>(5);


    static {
        Thread produceThread = new Thread(new CaptchaProducer());
        Thread cleanThread = new Thread(new CaptchaCleaner());
        produceThread.start();
        cleanThread.start();
        try {
            stringRedisTemplate = ApplicationUtil.getBean("stringRedisTemplate");
        } catch (Exception e) {
            log.error("inject error ", e);
        }

    }

    /**
     * @param account  学号
     * @param password 密码
     * @throws UrpRequestException 请求异常
     */
    public NewUrpSpider(String account, String password) {
        MDC.put("account", account);
        this.account = account;
        this.password = password;
        if (hasLoginCookieCache(account)) {
            return;
        }
        PreLoadCaptcha preLoadCaptcha;
        VerifyCode verifyCode = null;
        while ((preLoadCaptcha = queue.poll()) != null){
            if(System.currentTimeMillis() - preLoadCaptcha.createDate.getTime() < 1000*60*20){
                MDC.put("preLoad", preLoadCaptcha.preloadCookieId);
                verifyCode = preLoadCaptcha.captcha;
                break;
            }
        }
        if(verifyCode == null){
            verifyCode = getCaptcha();
        }

        Base64.Encoder encoder = Base64.getEncoder();

        UUID uuid = UUID.randomUUID();
        HashOperations<String, String, String> opsForHash = stringRedisTemplate.opsForHash();

        opsForHash.put(RedisKeys.CAPTCHA.getName(), uuid.toString(), encoder.encodeToString(verifyCode.getData().clone()));
        String code = CaptchaBreaker.getCode(uuid.toString());

        studentCheck(account, password, code, uuid.toString());

        opsForHash.delete(RedisKeys.CAPTCHA.getName(), uuid.toString());
    }

    /**
     * 获得补考成绩
     */
    public List<Map<String, Object>> getMakeUpGrade() {
        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("jxzxjh", "2019-2020-1-1")
                .add("ksbh", "2019-2020-1-1-01")
                .add("pageNum", "1")
                .add("pageSize", "10")
                .build();

        Request request = new Request.Builder()
                .url(MAKE_UP_GRADE)
                .post(body)
                .build();
        String result = new String(execute(request));
        try {
            return JSON.parseObject(result, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (JSONException e) {
            if (result.length() > 1000) {
                throw new UrpEvaluationException("account: " + account + " 未完成评估无法查成绩");
            }else if(result.contains("login")){
                COOKIE_JAR.clearSession();
                throw new UrpSessionExpiredException("account: " + account + "session expired");
            }
            log.error("parse grade error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + "session expired");
        }
    }

    public CurrentGrade getCurrentGrade() {
        List<UrpGeneralGradeForSpider> urpGeneralGradeForSpiders = getUrpGeneralGrades();
        List<UrpGradeForSpider> urpGradeForSpiderList = Lists.newArrayList();
        urpGeneralGradeForSpiders.forEach(urpGeneralGradeForSpider -> {
            UrpGradeForSpider urpGradeForSpider = getUrpGradeForSpider(urpGeneralGradeForSpider);
            urpGradeForSpiderList.add(urpGradeForSpider);
        });
        CurrentGrade currentGrade = new CurrentGrade();
        currentGrade.setList(urpGradeForSpiderList);
        return currentGrade;
    }

    private UrpGradeForSpider getUrpGradeForSpider(UrpGeneralGradeForSpider urpGeneralGradeForSpider) {
        UrpGradeForSpider urpGradeForSpider = new UrpGradeForSpider();
        urpGradeForSpider.setUrpGeneralGradeForSpider(urpGeneralGradeForSpider);
        urpGradeForSpider.setUrpGradeDetailForSpider(getUrpGradeDetail(urpGeneralGradeForSpider));
        return urpGradeForSpider;
    }

    private List<UrpGeneralGradeForSpider> getUrpGeneralGrades() {
        Request request = new Request.Builder()
                .url(CURRENT_TERM_GRADE)
                .get()
                .build();
        String result = new String(execute(request));
        try {
            List<Map<String, Object>> list = JSON.parseObject(result, new TypeReference<List<Map<String, Object>>>() {
            });
            JSONArray jsonArray = (JSONArray) list.get(0).get("list");
            return jsonArray.toJavaList(UrpGeneralGradeForSpider.class);
        } catch (JSONException e) {
            if (result.length() > 1000) {
                throw new UrpEvaluationException("account: " + account + " 未完成评估无法查成绩");
            }

            log.error("parse grade error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + "session expired");
        }
    }

    public UrpGradeDetailForSpider getUrpGradeDetail(UrpGeneralGradeForSpider urpGeneralGradeForSpider) {
        FormBody.Builder params = new FormBody.Builder();
        CourseRelativeInfo courseRelativeInfo = urpGeneralGradeForSpider.getId();
        FormBody body = params.add("zxjxjhh", courseRelativeInfo.getExecutiveEducationPlanNumber())
                .add("kch", courseRelativeInfo.getCourseNumber())
                .add("kssj", courseRelativeInfo.getExamtime())
                .add("kxh", urpGeneralGradeForSpider.getCoureSequenceNumber())
                .build();

        Request request = new Request.Builder()
                .url(CURRENT_TERM_GRADE_DETAIL)
                .post(body)
                .build();
        String result = new String(execute(request));
        return JSON.parseObject(result, gradeDetailTypeReference);
    }

    public UrpCourseForSpider getUrpCourse(String uid) {
        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("kch", uid).build();
        Request request = new Request.Builder()
                .url(COURSE_DETAIL)
                .post(body)
                .build();
        String result = new String(execute(request));
        flashCache();
        //因为爬虫爬取的结果是个集合，所以先转成list
        List<UrpCourseForSpider> courses = JSONArray.parseObject(result, courseTypeReference);
        //因为用uid查询，所以取第一个元素即可
        return courses.get(0);
    }

    public static VerifyCode getCaptcha() {
        Request request = new Request.Builder()
                .url(CAPTCHA)
                .header("Referer", LOGIN)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36")
                .get()
                .build();

        return new VerifyCode(execute(request));

    }

    /**
     * 登陆校验
     */
    private void studentCheck(String account, String password, String captcha, String uuid) {


        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("j_username", account)
                .add("j_password", password)
                .add("j_captcha", captcha)
                .build();

        Request request = new Request.Builder()
                .url(CHECK)
                .headers(HEADERS)
                .post(body)
                .build();

        Response response = getResponse(request);
        String location = response.header("Location");

        if (StringUtils.isEmpty(location)) {
            COOKIE_JAR.clearSession();
            throw new UrpRequestException("url: " + request.url().toString() + " code: " + response.code() + " cause: " + response.message());
        } else if (location.contains("badCaptcha")) {
            COOKIE_JAR.clearSession();
            throw new UrpVerifyCodeException("captcha: " + captcha + " code uuid :" + uuid);
        } else if (location.contains("badCredentials")) {
            throw new PasswordUncorrectException("account: " + account);
        } else if (location.contains("concurrentSessionExpired")) {
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + "session expired");
        }

        ValueOperations<String, String> opsForValue = stringRedisTemplate.opsForValue();
        opsForValue.set(RedisKeys.URP_LOGIN_COOKIE.genKey(account), uuid, 25L, TimeUnit.MINUTES);

    }

    /**
     * 获取空教室信息
     *
     * @param emptyRoomPost
     * @return
     */
    public EmptyRoomPojo getEmptyRoom(EmptyRoomPost emptyRoomPost) {
        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("weeks", emptyRoomPost.getWeeks())
                .add("executiveEducationPlanNumber", "2019-2020-1-1")
                .add("codeCampusListNumber", "01")
                .add("teaNum", emptyRoomPost.getTeaNum())
                .add("wSection", emptyRoomPost.getWSection())
                .add("pageNum",emptyRoomPost.getPageNum())
                .add("pageSize",emptyRoomPost.getPageSize())
                .build();
        Request request = new Request.Builder()
                .url(EMPTY_ROOM)
                .headers(HEADERS)
                .post(body)
                .build();
        String result = new String(execute(request));
        try {
            List<EmptyRoomPojo> pojo=JSON.parseObject(result, new TypeReference<List<EmptyRoomPojo>>() {
            });
            return pojo.get(0);
        } catch (JSONException e) {
            if (result.length() > 1000) {
                throw new UrpEvaluationException("account: " + account + " 未完成评估无法查成绩");
            }
            log.error("parse grade error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + "session expired");
        }
    }

    public List<UrpExamTime> getExamTime() {
        Request request = new Request.Builder()
                .url(EXAM_TIME)
                .headers(HEADERS)
                .get()
                .build();
        String s = new String(execute(request));
        if (s.contains("invalidSession")) {
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + "session expired");
        }
        Document document = Jsoup.parse(s);
        Elements elements = document.getElementsByClass("clearfix");
        List<UrpExamTime> result = Lists.newArrayListWithExpectedSize(elements.size());
        for (Element element : elements) {
            List<String> list = SPACE_SPLITTER.splitToList(element.text());
            if (list.size() == 7) {
                result.add(new UrpExamTime()
                        .setCourseName(list.get(0))
                        .setExamName(list.get(1)));
            }
            if (list.size() == 11) {
                result.add(new UrpExamTime()
                        .setCourseName(list.get(1))
                        .setExamName(list.get(2))
                        .setWeekOfTerm(list.get(4))
                        .setDate(list.get(5))
                        .setWeek(list.get(6))
                        .setExamTime(list.get(7))
                        .setLocation(list.get(8)));
            }

        }
        return result;
    }

    public UrpCourseTimeTableForSpider getUrpCourseTimeTable() {
        Request request = new Request.Builder()
                .url(COURSE_TIME_TABLE)
                .headers(HEADERS)
                .get()
                .build();
        String result = new String(execute(request));
        String regex = "\"dateList\": [.*]}$";
        result = result.replaceAll(regex, "");
        try {
            return JSON.parseObject(result, UrpCourseTimeTableForSpider.class);
        } catch (JSONException e) {
            log.error("parse courseTimeTable error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + " session expired");
        }
    }

    public UrpStudentInfo getStudentInfo() {

        Request request = new Request.Builder()
                .url(STUDENT_INFO)
                .headers(HEADERS)
                .get()
                .build();

        Map<String, String> userInfo = parseUserInfo(new String(execute(request)));

        UrpStudentInfo student = new UrpStudentInfo();
        student.setAccount(Integer.parseInt(account));
        student.setEthnic(userInfo.get("民族"));
        student.setSex(userInfo.get("性别"));
        student.setName(userInfo.get("姓名"));
        student.setMajor(userInfo.get("专业"));
        student.setAcademy(userInfo.get("院系"));
        student.setClassname(userInfo.get("班级"));
        student.setPassword(password);
        flashCache();
        return student;
    }

    public List<ClassInfoSearchResult> getClassInfoSearchResult(){
        FormBody.Builder params = new FormBody.Builder();
        FormBody body = params.add("param_value", "100024")
                .add("executiveEducationPlanNum", "2019-2020-1-1")
                .add("pageNum", "1")
                .add("pageSize", "1000")
                .build();

        Request request = new Request.Builder()
                .url(CLASS_INFO)
                .headers(HEADERS)
                .post(body)
                .build();
        String result = new String(execute(request));
        try {
            return JSON.parseObject(result, classInfoReference);
        } catch (JSONException e) {
            log.error("parse courseTimeTable error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + " session expired");
        }


    }


    public List<List<ClassCourseSearchResult>> getUrpCourseTimeTableByClassCode(String classCode) {

        Request request = new Request.Builder()
                .url(SEARCH_COURSE_INFO + "?planCode=2019-2020-1-1&classCode=" + classCode)
                .headers(HEADERS)
                .get()
                .build();
        String result = new String(execute(request));
        try {
            return JSONArray.parseObject(result, classCourseSearchResultReference);
        } catch (JSONException e) {
            log.error("parse courseTimeTable error {}", result, e);
            COOKIE_JAR.clearSession();
            throw new UrpSessionExpiredException("account: " + account + " session expired");
        }

    }


    /**
     * 解析学生信息页面的html
     */
    private Map<String, String> parseUserInfo(String html) {
        HashMap<String, String> infoMap = new HashMap<>();
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("profile-info-row");
        for (Element e : elements) {
            Elements name = e.getElementsByClass("profile-info-name");
            List<Element> nameList = Lists.newArrayList(name.iterator());
            Elements value = e.getElementsByClass("profile-info-value");
            List<Element> valueList = Lists.newArrayList(value.iterator());


            for (int x = 0; x < nameList.size(); x++) {
                infoMap.put(nameList.get(x).text(), valueList.get(x).text());
            }
        }

        return infoMap;
    }


    private static byte[] execute(Request request) {
        try (Response response = CLIENT.newCall(request).execute()) {
            if (isResponseFail(response)) {
                throw new UrpRequestException("url: " + request.url().toString() + " code: " + response.code() + " cause: " + response.message());
            }
            return response.body().bytes();
        } catch (IOException e) {
            throw new UrpTimeoutException(request.url().toString(), e);
        }
    }


    private Response getResponse(Request request) {
        try (Response response = CLIENT.newCall(request).execute()) {
            if (isResponseFail(response)) {
                throw new UrpRequestException("url: " + request.url().toString() + " code: " + response.code() + " cause: " + response.message());
            }
            return response;
        } catch (IOException e) {
            throw new UrpTimeoutException(request.url().toString(), e);
        }
    }

    private boolean hasLoginCookieCache(String account) {

        return stringRedisTemplate.hasKey(RedisKeys.URP_COOKIE.genKey(account))
                && stringRedisTemplate.hasKey(RedisKeys.URP_LOGIN_COOKIE.genKey(account));
    }

    /**
     * @param response 响应
     * @return 是否成功响应
     */
    private static boolean isResponseFail(Response response) {
        return response.body() == null ||
                (!response.isSuccessful() && !response.isRedirect());
    }

    private void flashCache() {
        stringRedisTemplate.expire(RedisKeys.URP_LOGIN_COOKIE.genKey(account), 20L, TimeUnit.MINUTES);
    }


    private static class CaptchaProducer implements Runnable{

        @Override
        public void run() {
            while (!Thread.interrupted()){
                log.debug("produce captcha thread start");
                UUID uuid = UUID.randomUUID();
                MDC.put("preLoad", uuid.toString());
                try {
                    VerifyCode captcha = getCaptcha();
                    PreLoadCaptcha preLoadCaptcha = new PreLoadCaptcha(captcha, uuid.toString(), new Date());
                    queue.put(preLoadCaptcha);
                } catch (Throwable e) {
                    log.error("preload captcha error", e);
                } finally {
                    MDC.clear();
                    log.debug("captcha queue size {}", queue.size());
                }

            }
        }
    }

    private static class CaptchaCleaner implements Runnable{

        @Override
        public void run() {
            while (!Thread.interrupted()){
                try {
                    Thread.sleep(1000 * 60 * 20);
                } catch (Throwable e) {
                    log.error("clean preload captcha error", e);
                }
                log.debug("clean up thread start");
                queue.removeIf(PreLoadCaptcha::isExpire);
            }
        }
    }

    @Data
    private static class PreLoadCaptcha{
        private VerifyCode captcha;
        private String preloadCookieId;
        private Date createDate;

        PreLoadCaptcha(VerifyCode captcha, String preloadCookieId, Date createDate){
            this.captcha = captcha;
            this.preloadCookieId = preloadCookieId;
            this.createDate = createDate;
        }

        boolean isExpire(){
            return System.currentTimeMillis() - createDate.getTime() > 1000 * 60 * 20;
        }
    }

}