package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.pojo.constant.Academy;
import cn.hkxj.platform.pojo.constant.Building;
import cn.hkxj.platform.pojo.constant.CourseType;
import cn.hkxj.platform.pojo.constant.Direction;
import cn.hkxj.platform.pojo.example.ClassesExample;
import cn.hkxj.platform.pojo.example.CourseExample;
import cn.hkxj.platform.pojo.example.CourseTimeTableExample;
import cn.hkxj.platform.pojo.example.RoomExample;
import cn.hkxj.platform.pojo.timetable.CourseTimeTable;
import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.util.*;

/**
 * @author Yuki
 * @date 2019/3/18 21:25
 */
@Slf4j
@Service
public class ExcelService {

    @Resource
    private ClassesMapper classesMapper;
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseTimeTableMapper courseTimeTableMapper;
    @Resource
    private RoomMapper roomMapper;
    @Resource
    private ClassTimeTableMapper classTimeTableMapper;

    //班级和相关的课程时间表的映射
    private static Map<Integer, Set<Integer>> classes_timetable = new HashMap<>();
    //包含专业对应的专业号和学院号
    private static Map<String, int[]> subjectAndAcademy = new HashMap<>();

    /**
     * 初始化classes_timetable和subjectAndAcademy
     */
    public void init(){
        List<Classes> classesList = classesMapper.selectByExample(new ClassesExample());
        if(classesList.size() == 0){
            throw new RuntimeException();
        }
        for (Classes classes : classesList) {
            subjectAndAcademy.put(classes.getName(), new int[]{classes.getSubject(), classes.getAcademy()});
        }
    }

    public CourseTimeTable getOppositeCourseTimetable(CourseTimeTable courseTimeTable){
        CourseTimeTableExample courseTimeTableExample = new CourseTimeTableExample();
        courseTimeTableExample.createCriteria()
                .andCourseEqualTo(courseTimeTable.getCourse())
                .andTermEqualTo(courseTimeTable.getTerm())
                .andYearEqualTo(courseTimeTable.getYear())
                .andStartEqualTo(courseTimeTable.getStart())
                .andEndEqualTo(courseTimeTable.getEnd())
                .andOrderEqualTo(courseTimeTable.getOrder())
                .andWeekEqualTo(courseTimeTable.getWeek())
                .andDistinctEqualTo(courseTimeTable.getDistinct())
                .andRoomEqualTo(courseTimeTable.getRoom());
        List<CourseTimeTable> courseTimeTables = courseTimeTableMapper.selectByExample(courseTimeTableExample);
        if(!CollectionUtils.isEmpty(courseTimeTables)){
            return courseTimeTables.get(0);
        }
        courseTimeTableMapper.insertSelective(courseTimeTable);
        return courseTimeTable;
    }

    /**
     * 将教室名分离，start和end是用来把方向提取出来 如WN0401 start:0 end = 2;
     * type 是否有相应的方向 科401就没有相应的方向
     * @param location 教室名
     * @return 包含start, end, type的数组
     */
    private int[] getLocationIndexs(String location){
        int start = 0;//第一个英文字符的下标
        int end = 0;//提取方向时用到的下标
        for(int i = 0; i < location.length(); i++){
            //判断是否英文字符
            if(isAlphabet(location.charAt(i))){
                start = i;
                end = i + 1;
                //判断当前字符的下一个字符是不是英文字符
                if(isAlphabet(location.charAt(end))) {
                    end++;
                }
                break;
            }
        }
        return new int[]{start, end};
    }

    /**
     * 解析教室后封装成Room对象返回
     * @param location 教室名
     * @return Room
     */
    public Room parseBuilding(String location){
        int[] numbers = getLocationIndexs(location);
        Room room = specialLocation(location, numbers);
        if(room != null){
            return room;
        }
        //科S212   图书馆S4004 主楼E0305 科401 （高层）
        //建筑物名
        String building = location.substring(0, numbers[0]);
        if(building.equals("科")) building = "科厦";
        String direction = location.substring(numbers[0], numbers[1]);
        int code = getCodeByDirection(direction);
        //计算楼层和房间号
        int floorAndNumber = Integer.parseInt(location.substring(numbers[1], location.length()));
        int floor = floorAndNumber / 100;
        int number = floorAndNumber % 100;
        room = new Room();
        room.setArea(Building.getBuildingByName(building));
        room.setDirection(Direction.getDirectionByCode(code));
        room.setFloor(floor);
        room.setNumber(number);
        room.setName(location);
        room.setIsAllow((byte) 1);
        return room;
    }

    /**
     * 对一些特殊教室名进行解析
     * @param location 教室名
     * @param numbers 解析用的一些下标
     * @return 相应的对象
     */
    private Room specialLocation(String location, int[] numbers){
        Room room;
        //图书馆
        if(location.startsWith("图书馆")){
            room = new Room();
            String direction = location.substring(numbers[0], numbers[1]);
            int floorAndNumber = Integer.parseInt(location.substring(numbers[1], location.length()));
            int floor = floorAndNumber / 1000;
            int number = floorAndNumber % 100;
            room.setDirection(Direction.getDirectionByCode(getCodeByDirection(direction)));
            room.setIsAllow((byte)0);
            room.setNumber(number);
            room.setFloor(floor);
            room.setArea(Building.getBuildingByName("图书馆"));
            room.setName(location);
            return room;
        }
        //科高
        if(location.startsWith("科") && location.length() > 5){
            int floorAndNumber = Integer.parseInt(location.substring(1, 4));
            int floor = floorAndNumber / 100;
            int number = floorAndNumber % 100;
            room = new Room();
            room.setDirection(Direction.CORRECT);
            room.setIsAllow((byte)0);
            room.setNumber(floor);
            room.setFloor(number);
            room.setArea(Building.getBuildingByName("科高"));
            room.setName(location);
            return room;
        }
        //各种实验室
        if(!location.startsWith("主楼") && !location.startsWith("科")){
            room = new Room();
            room.setDirection(Direction.CORRECT);
            room.setIsAllow((byte)0);
            room.setNumber(0);
            room.setFloor(0);
            room.setArea(Building.getBuildingByName("实验室"));
            room.setName(location);
            return room;
        }
        return null;
    }

    /**
     * 通过课程号在course表查找是否有相应的课程信息，有就直接返回，没有就插入记录
     * @param excelResult 封装好的excel的行结果
     * @param distinct 单双周
     * @return 相应的课程信息
     */
    private Course getOppositeCourse(ExcelResult excelResult, int distinct){
        CourseExample example = new CourseExample();
        example.createCriteria()
                .andUidEqualTo(excelResult.getCourse_id());
        List<Course> courses = courseMapper.selectByExample(example);
        Course course;
        if(courses.size() == 0){
            //没有相应的课程信息就插入新的新纪录
            course = new Course();
            course.setName(excelResult.getCourse_name());
            course.setUid(excelResult.getCourse_id());
            course.setAcademy(Academy.getAcademyBySimpleName(excelResult.getAcademy()));
            course.setCredit(0);
            course.setType(CourseType.getCourseByByte(distinct));
            courseMapper.insertSelective(course);
        } else {
            //否则直接返回
            course = courses.get(0);
        }
        return course;
    }

    /**
     * 根据教室名在数据库中查找相应教室信息
     * 没有处理和实验室相关的所有教室名称
     * @param excelResult 封装好的excel的行结果
     * @return Room信息
     */
    private Room getOppositeRoom(ExcelResult excelResult){
        RoomExample roomExample = new RoomExample();
        roomExample.createCriteria()
                .andNameEqualTo(excelResult.getLocation());
        List<Room> rooms = roomMapper.selectByExample(roomExample);
        Room room;
        if(rooms.size() > 0){
            room = rooms.get(0);
        } else {
            room = parseBuilding(excelResult.getLocation());
            roomMapper.insertSelective(room);
        }
        return room;
    }

    /**
     * 插入相应的课程时间表数据
     * @param course 相应的课程信息
     * @param room 相应的教室信息
     * @param excelResult 对应的excel行数据
     * @return 返回插入完之后的课表时间表的id集合
     */
    private Set<Integer> insertCourseTimetable(Course course, Room room, ExcelResult excelResult){
        Set<Integer> timetableIds = new HashSet<>();
        Map<String, Integer> startAndStopMap = excelResult.getStartAndStopTime();
        int count = startAndStopMap.size() / 2;
        for(int i = 1; i <= count; i++){
            CourseTimeTable courseTimeTable = new CourseTimeTable();
            courseTimeTable.setTerm(2);
            courseTimeTable.setYear(2019);
            courseTimeTable.setCourse(course);
            courseTimeTable.setWeek(excelResult.getWeek());
            courseTimeTable.setOrder(excelResult.getOrder());
            courseTimeTable.setDistinct(excelResult.getStartAndStopTime().get("distinct"));
            courseTimeTable.setRoom(room);
            courseTimeTable.setStart(excelResult.getStartAndStopTime().get("start" + i));
            courseTimeTable.setEnd(excelResult.getStartAndStopTime().get("end" + i));
            System.out.println("courseTimetable     " + courseTimeTable);
            courseTimeTable = getOppositeCourseTimetable(courseTimeTable);
//            courseTimeTableMapper.insertSelective(courseTimeTable);
            timetableIds.add(courseTimeTable.getId());

        }
        return timetableIds;
    }

    /**
     * 将班级列表的班级信息逐一查表,有则继续下一步，没有就插入相应的数据
     * @param classes 班级实体
     */
    private void isNeedInsertClasses(Classes classes){
        if(Objects.isNull(classes.getId())){
            ClassesExample classesExample = new ClassesExample();
            classesExample.createCriteria()
                    .andYearEqualTo(classes.getYear())
                    .andNumEqualTo(classes.getNum())
                    .andNameEqualTo(classes.getName());
            List<Classes> cs = classesMapper.selectByExample(classesExample);
            if(cs.size() == 0){
                classesMapper.insertSelective(classes);
            }
        }
    }

    /**
     * 在class_timetable表中查询相应数据
     */
    private void insertClassIdAndTimetableId(){
        for(Map.Entry<Integer, Set<Integer>> entry : classes_timetable.entrySet()){
            Set<Integer> set = entry.getValue();
            for(Integer id : set){
                if(Objects.isNull(entry.getKey())) { continue; }
                log.info("class_timetable   class_id" + entry.getKey() + "      timetable_id" + id);
                classTimeTableMapper.insert(entry.getKey(), id);
            }
        }
    }

    /**
     * 进行最后的插入数据的工作
     * @param excelResults 包含所有封装后的excel数据
     */
    public void insertDb(List<ExcelResult> excelResults){
        for(ExcelResult excelResult : excelResults){
            List<Classes> classesList = excelResult.getClassesList();
            //如果没有相应的班级列表，则直接跳过
            if(classesList.size() == 0) continue;
            Map<String, Integer> map = excelResult.getStartAndStopTime();
            //处理课程信息
            Course course = getOppositeCourse(excelResult, map.get("distinct"));
            //处理教室信息
            Room room = getOppositeRoom(excelResult);
            //插入相应的课程时间表，没有和数据库做比对
            Set<Integer> timetableIds = insertCourseTimetable(course, room, excelResult);
            for(Integer id : timetableIds){
                if(id == 5899 || id == 5900){
                    System.out.println("5899  ||| 5900");
                }
            }
            //处理班级信息
            processClassesAndCourseTimetableMapping(classesList, timetableIds);
        }
        //在class_timetable表中查询相应数据
        insertClassIdAndTimetableId();
    }

    private void processClassesAndCourseTimetableMapping(List<Classes> classesList, Set<Integer> timetableIds){
        for(Classes classes : classesList){
            if(StringUtils.isEmpty(classes.getClassname())){ continue; }
            //将班级信息在数据库中查找，没有找到就插入数据
            isNeedInsertClasses(classes);
            //建立班级和课程时刻表的映射
            Set<Integer> ids = classes_timetable.get(classes.getId());
            if(ids == null || ids.size() == 0){
                ids = new HashSet<>(timetableIds);
                classes_timetable.put(classes.getId(), ids);
            } else {
                ids.addAll(timetableIds);
                classes_timetable.put(classes.getId(), ids);
            }
        }
    }

    /**
     * 处理Excel文件的数据并封装成ExcelResult的实体中
     */
    public  List<ExcelResult> readExcel(){
        init();
        FileInputStream fis;
        Workbook workbook;
        try {
            fis=new FileInputStream("C:\\Users\\Yuki\\Desktop\\course\\18-19-2全校大课表 - 副本.xls");
            workbook = new HSSFWorkbook(fis);
            Sheet sheet=workbook.getSheetAt(0);
            int rowNum=sheet.getLastRowNum();
            List<ExcelResult> excelResults = new ArrayList<>();
            for(int i = 1; i < rowNum; i++){
                Row row=sheet.getRow(i);
                if(i == 1884){
                    Map map = new HashMap();
                }
                log.info("current excel row number {}", i);
                ExcelResult excelResult = new ExcelResult();
                excelResult.setId((int) Double.parseDouble(String.valueOf(row.getCell(0)).trim()));
                excelResult.setCourse_id(String.valueOf(row.getCell(1)).trim());
                excelResult.setCourse_num((int) Double.parseDouble(String.valueOf(row.getCell(2)).trim()));
                excelResult.setCourse_name(String.valueOf(row.getCell(3)).trim());
                excelResult.setT_name(String.valueOf(row.getCell(4)).trim());
                excelResult.setClassesList(parseClassname(String.valueOf(row.getCell(5)).trim()));
                excelResult.setAcademy(String.valueOf(row.getCell(6)).trim());
                excelResult.setStartAndStopTime(getStartAndStopTime(String.valueOf(row.getCell(7)).trim()));
                excelResult.setWeek((int) Double.parseDouble(String.valueOf(row.getCell(8)).trim()));
                excelResult.setOrder((int) Double.parseDouble(String.valueOf(row.getCell(9)).trim()));
                excelResult.setLocation(String.valueOf(row.getCell(11)).trim());
                excelResults.add(excelResult);
                log.info("current excel row {}", excelResult);
            }
            return excelResults;
        }catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 将传入的字符串解析开始时间、结束时间和单双周
     * @param str 要解析的字符串
     * @return map
     */
    private Map<String, Integer> getStartAndStopTime(String str){
        //先按照,的规则分割
        StringTokenizer stringTokenizer = new StringTokenizer(str, ",");
        int count = stringTokenizer.countTokens();
        int tag = 1;
        Map<String, Integer> startAndStop = new HashMap<>();
        //count等于一说明只有一个其实时间 如：1-4周上
        if(count == 1){
            getWeekMap(stringTokenizer.nextToken(), tag, startAndStop);
            return startAndStop;
        }
        String lastStr = "";
        while(stringTokenizer.hasMoreTokens()) {
            if(tag == count){
                lastStr = stringTokenizer.nextToken();
                break;
            }
            String cstr = stringTokenizer.nextToken();
            //按照-的规则切分字符串，字符串有两种形式如:5 , 1-16周X周上
            StringTokenizer st = new StringTokenizer(cstr, "-");
            if(st.countTokens() == 1){
                int token = Integer.parseInt(st.nextToken());
                startAndStop.put("start" + tag, token);
                startAndStop.put("end" + tag, token);
            } else {
                startAndStop.put("start" + tag, Integer.parseInt(st.nextToken()));
                startAndStop.put("end" + tag, Integer.parseInt(st.nextToken()));
            }
            tag++;
        }
        getWeekMap(lastStr, tag, startAndStop);
        return startAndStop;
    }

    /**
     * 处理字符串，如 1-16周X周上
     * @param string 要解析的字符串
     * @param tag 标识
     * @param original 将数据传入目标map
     */
    private void getWeekMap(String string, int tag, Map<String, Integer> original){
        //横线下标
        int horizontal = string.lastIndexOf("-");
        //最后一个数字字符对应的下标
        int index = lastIndexOfNum(string);
        //表示单双周的字符下表
        int distinctIndex = index + 2;
        //这里是为了后面切割字符串时使用
        index = index - horizontal;
        //有部分字符串还变成1-16周X周的形式，所以比较一下防止出错
        if(distinctIndex == string.length()){
            distinctIndex--;
        }
        //横线下标为-1时，说明字符串为5这种形式
        if(horizontal == -1){
            original.put("start" + tag, Integer.parseInt(string.substring(0, index)));
            original.put("end" + tag, Integer.parseInt(string.substring(0, index)));
            original.put("distinct", isNormalOrSingleOrDouble(string.charAt(distinctIndex)));
        } else {
            StringTokenizer stringTokenizer = new StringTokenizer(string, "-");
            original.put("start" + tag, Integer.parseInt(stringTokenizer.nextToken()));
            original.put("end" + tag, Integer.parseInt(stringTokenizer.nextToken().substring(0, index)));
            original.put("distinct", isNormalOrSingleOrDouble(string.charAt(distinctIndex)));
        }
    }

    /**
     * 解析字符串返回班级列表
     * @param name 要解析的字符串
     * @return 班级列表
     */
    private List<Classes> parseClassname(String name){
        if(isUndesiredSpecialName(name)) { return new ArrayList<>(); }
        String[] classes = name.split(" ");
        List<Classes> classesList = new ArrayList<>();
        for(String classname : classes){
            if(classname.endsWith("班")){
                classname = classname.substring(0, classname.length() -1);
            }
            //如果和某些特殊班级对应，处理完就进行下一次解析
            if(isSpecialNameForParse(classname, classesList)){
                continue;
            }
            //classname的形式电技17-1-4
            String[] str = classname.split("-");
            Classes target = getMajorAndYear(str[0]);
            //具体的解析过程
            concreteAnalysis(str, target, classesList);
        }
        return classesList;
    }

    /**
     * 班级名解析的具体过程。
     * @param str 分割后的字符串
     * @param target 包含专业名和年级的Classes对象
     * @param classesList 班级列表集合
     */
    private void concreteAnalysis(String[] str, Classes target, List<Classes> classesList){
        int start = Integer.valueOf(str[1]);    //最小的班级序号
        int end = Integer.valueOf(str[str.length - 1]); //最大的班级序号
        for(int j = start; j <= end; j++){
            String classes_name = target.getName();
            int classes_year = target.getYear();
            ClassesExample example = new ClassesExample();
            example.createCriteria()
                    .andNumEqualTo(j)
                    .andYearEqualTo(classes_year)
                    .andNameEqualTo(classes_name);
            List<Classes> classesList1 = classesMapper.selectByExample(example);
            if(classesList1.size() > 0){
                classesList.add(classesList1.get(0));
            } else {
                int[] sa = subjectAndAcademy.get(target.getName());
                Classes classes1 = new Classes();
                classes1.setName(classes_name);
                classes1.setNum(j);
                classes1.setYear(classes_year);
                classes1.setSubject(sa[0]);
                classes1.setAcademy(sa[1]);
                classesList.add(classes1);
            }
        }
    }

    /**
     * 具体解析过程中对特殊的班级名称再过滤
     * @param classname 班级名称
     * @param classesList 班级列表
     * @return 是否匹配成功
     */
    private boolean isSpecialNameForParse(String classname, List<Classes> classesList){
        if(classname.startsWith("财会S")){
            ClassesExample example = new ClassesExample();
            example.createCriteria()
                    .andNameEqualTo(classname);
            List<Classes> classesList1 = classesMapper.selectByExample(example);
            if(classesList1.size() > 0){
                classesList.add(classesList1.get(0));
                return true;
            }
        }
        return false;
    }

    /**
     * 因为有些课程不需要录入，对相应的班级名称进行检测
     * @param name 班级名称
     * @return 是否特殊
     */
//    private List<Classes> isSpecialName(String name){
//        List<Classes> result = new ArrayList<>();
//        if(StringUtils.isEmpty(name)){
//            return result;
//        }
//        if(name.startsWith("全校")){
//            return result;
//        }
//        if(isNumber(name.charAt(0))){
//            return result;
//        }
//        if(name.startsWith("财会S")){
//            //String year = name.substring(3, name.length());
//            ClassesExample example = new ClassesExample();
//            example.createCriteria()
//                    .andNameEqualTo(name);
//            List<Classes> classesList = classesMapper.selectByExample(example);
//            if(classesList.size() > 0){
//                result = new ArrayList<>();
//                result.add(classesList.get(0));
//                return result;
//            }
//        }
//        return result;
//    }

    /**
     * 判断是否不需要进行处理的特殊班级名
     * @param name
     * @return
     */
    private boolean isUndesiredSpecialName(String name){
        //1.班级名称为空
        //2.全校开头
        //3.数字开头
        return StringUtils.isEmpty(name) || name.startsWith("全校") || isNumber(name.charAt(0));
    }

    /**
     * 解析字符串后将专业名和年级放入一个字符串数组
     * @param str 要解析的字符串
     * @return Classes
     */
    private Classes getMajorAndYear(String str){
        Classes classes = new Classes();
        String[] strs = {};
        for(int i = 0; i < str.length(); i++){
            if(isNumber(str.charAt(i))) {
                strs = new String[]{str.substring(0, i), str.substring(i, str.length())};
                break;
            }
        }
        classes.setName(strs[0]);
        classes.setYear(Integer.parseInt(strs[1]));
        return classes;
    }

    private boolean isNumber(char a){
        return '0' <= a && a <= '9';
    }

    private boolean isAlphabet(char a){
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }

    private int isNormalOrSingleOrDouble(char i){
        if(i == '单'){
            return 1;
        } else if(i == '双'){
            return 2;
        } else{
            return 0;
        }
    }

    public int lastIndexOfNum(String str){
        if(Objects.isNull(str)){
            throw new IllegalArgumentException("input object should not be null");
        }
        int length = str.length();
        int index = 0;
        for(int i = 0; i < length; i++){
            if(isNumber(str.charAt(i))){
               index = i;
            }
        }
        return index;
    }

    private int getCodeByDirection(String direction){
        switch (direction){
            case "N":
                return 1;
            case "S":
                return 2;
            case "E":
                return 3;
            case "W":
                return 4;
            case "EN":
                return 5;
            case "ES":
                return 6;
            case "WN":
                return 7;
            case "WS":
                return 8;
            default:
                throw new IllegalArgumentException("no code match");
        }
    }

}

@Data
class ExcelResult {

    private int id;
    private String course_id; //课程号
    private int course_num; //课序号
    private String course_name;
    private String t_name;
    private List<Classes> classesList;
    private String academy;
    private Map<String, Integer> startAndStopTime;
    private int week;
    private int order;
    private String location;
}


