package cn.hkxj.platform.spider;

import cn.hkxj.platform.exceptions.PasswordUncorrectException;
import cn.hkxj.platform.pojo.AllGradeAndCourse;
import cn.hkxj.platform.pojo.Course;
import cn.hkxj.platform.pojo.CourseType;
import cn.hkxj.platform.pojo.Grade;
import cn.hkxj.platform.utils.ReadProperties;
import cn.hkxj.platform.utils.TypeUtil;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author JR Chan
 * @date 2018/6/4 21:06
 */

@Slf4j
public class AppSpider {
	/**
	 * 从APP接口上获取token，提供获取成绩，考试安排以及课程表三个方法
	 * 使用的时候需要先设置账号或者token，如果直接设置了token就不需要再获取。getToken会自动把token写进实例变量中。
	 * 获取数据时可以填入指定token作参数，不填入默认使用初始化生成token。
	 * 如果有密码错误异常，如果能设置密码需要自己声明
	 */
	private int account;
	private String password;
	private String token;
	private final static String KEY = ReadProperties.get("appspider.key");
	private final static Gson GSON = new Gson();
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private final static String URL_ROOT = "http://222.171.107.108";
	private final static String LOGIN = URL_ROOT + "//university-facade/Murp/Login";
	private final static String GRADE = URL_ROOT + "//university-facade/MyUniversity/MyGrades";
	private final static String LESSON = URL_ROOT + "//university-facade/MyUniversity/MyLessons";
	private final static String SCHEDULE = URL_ROOT + "//university-facade/Schedule/ScheduleList";
	private final static String EXAM = URL_ROOT + "//university-facade/MyUniversity/Exam";
	private final static OkHttpClient CLIENT = new OkHttpClient.Builder()
			.connectTimeout(5, TimeUnit.SECONDS)
			.build();

	private final static Headers HEADERS = new Headers.Builder()
			.add("Host", "222.171.107.108")
			.add("Connection", "Keep-Alive")
			.add("Accept-Encoding", "gzip")
			.add("User-Agent", "okhttp/3.3.1")
			.build();

	public AppSpider(int account) {
		this.account = account;
		this.password = String.valueOf(account) + KEY;
	}

	public AppSpider(int account, String password) {
		this.account = account;
		this.password = password + KEY;
	}

	public AppSpider(@NonNull String token) {
		this.token = token;
	}

	/**
	 * 使用默认密码是否返回合法token来判断是否使用已经修改密码
	 *
	 * @return
	 */
	public boolean isValidPassword() {

		return false;
	}

	private void checkPassword() throws PasswordUncorrectException {
		RequestBody loginRequestBody = getLoginRequestBody();
		Map result = postData(LOGIN, loginRequestBody);
	}

	public String getToken() throws PasswordUncorrectException {
		if (token != null) {
			return token;
		}

		RequestBody loginRequestBody = getLoginRequestBody();
		Map result = postData(LOGIN, loginRequestBody);
		Map data = (Map) result.get("data");
		String token = (String) data.get("token");
		this.token = token;

		return token;
	}

	public ArrayList getGrade() {
		String url = GRADE + "?token=" + token;
		Map data = getData(url);

		new AppGradeResult((ArrayList) data.get("data"));

		return (ArrayList) data.get("data");
	}

	public ArrayList getLesson() {
		String url = LESSON + "?token=" + token;
		Map data = getData(url);
		return (ArrayList) data.get("data");
	}

	public Map getSchedule() {
		String url = SCHEDULE + "?token=" + token;
		Map data = getData(url);
		return (Map) data.get("data");
	}

	public ArrayList getExam() {
		String url = EXAM + "?token=" + token;
		Map data = getData(url);
		return (ArrayList) data.get("data");
	}

	public AllGradeAndCourse getGradeAndCourse() {
		AllGradeAndCourse allGradeAndCourse = new AllGradeAndCourse();
		for (Object item : getGrade()) {
			Map itemMap = (Map) item;
			ArrayList<Map> items = (ArrayList) itemMap.get("items");
			String xn = itemMap.get("xn").toString();
			String xq = itemMap.get("xq").toString();
			ArrayList<AllGradeAndCourse.GradeAndCourse> gradeAndCourseList = new ArrayList<>();
			for (Map detail : items) {

				String uid = detail.get("kcdm").toString();
				String type = detail.get("kcxz").toString();
				String name = detail.get("kcmc").toString();
				String cj = detail.get("cj").toString();
				Double xf = (Double) detail.get("xf");

				Grade grade = getGrade(uid, cj, xf, xq, xn);
				Course course = getCourse(uid, name, type, xf);
				AllGradeAndCourse.GradeAndCourse gradeAndCourse = new AllGradeAndCourse.GradeAndCourse();
				gradeAndCourse.setCourse(course);
				gradeAndCourse.setGrade(grade);
				gradeAndCourseList.add(gradeAndCourse);
			}
			allGradeAndCourse.addGradeAndCourse(gradeAndCourseList);
		}

		return allGradeAndCourse;
	}

	private Course getCourse(String uid, String name, String type, double xf) {
		Course course = new Course();
		course.setUid(uid);
		course.setName(name);
		course.setType(CourseType.getCourseByType(type));
		course.setCredit(TypeUtil.pointToInt(xf));

		return course;
	}

	private Grade getGrade(String uid, String cj, double xf, String xq, String xn) {
		Grade grade = new Grade();
		grade.setAccount(account);
		grade.setCourseId(uid);
		grade.setScore(TypeUtil.gradeToInt(cj));
		grade.setPoint(TypeUtil.pointToInt(xf));
		grade.setTerm(new Byte(xq));
		grade.setYear(xnToYear(xn));

		return grade;
	}


	private RequestBody getLoginRequestBody() {

		HashMap<String, String> postData = Maps.newHashMapWithExpectedSize(2);
		try {
			postData.put("u", String.valueOf(account));
			postData.put("p", DigestUtils.md5Hex(password.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException("UnsupportedEncoding in password", e);
		}
		String json = GSON.toJson(postData);

		return RequestBody.create(JSON, json);
	}

	private Map getData(String url)  {
		Request request = new Request.Builder()
				.url(url)
				.headers(HEADERS)
				.build();
		try {
			return execute(request);
		} catch (PasswordUncorrectException e) {
			throw new RuntimeException("app spider password not correct account:"+account);
		}
	}

	private Map postData(String url, RequestBody requestBody) throws PasswordUncorrectException {
		Request request = new Request.Builder()
				.url(url)
				.headers(HEADERS)
				.post(requestBody)
				.build();
		return execute(request);

	}

	private Map execute(Request request) throws PasswordUncorrectException {
		Response response;
		String data;
		try {
			response = CLIENT.newCall(request).execute();
			data = response.body().string();
		} catch (IOException e) {
			throw new RuntimeException("AppSpider fail in execute request", e);
		}


		Map resultMap = GSON.fromJson(data, Map.class);

		int state = ((Double) resultMap.get("state")).intValue();

		if (state == 2002) {
			throw new PasswordUncorrectException();
		}

		if (state != 200) {
			String msg = (String) resultMap.get("message");
			throw new IllegalArgumentException(String.valueOf(state)+':'+msg);
		}

		return resultMap;
	}

	private static int xnToYear(String xn) {
		String[] split = xn.split("-");
		return Integer.parseInt(split[0]);
	}

	//如果全部错误都在那抛出非检查异常，有的是可以处理的，但是有的不行
	//单独再提出出来细分

}
