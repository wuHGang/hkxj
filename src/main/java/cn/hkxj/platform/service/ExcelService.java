package cn.hkxj.platform.service;

import cn.hkxj.platform.mapper.*;
import cn.hkxj.platform.pojo.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.util.*;

/**
 * @author Yuki
 * @date 2019/3/18 21:25
 */
@Slf4j
@Service
public class ExcelService {

    @Autowired
    private ClassesMapper classesMapper;

    @Autowired
    private CourseMapper courseMapper;

    @Autowired
    private CourseTimeTableMapper courseTimeTableMapper;

    @Autowired
    private RoomMapper roomMapper;

    @Autowired
    private ClassTimeTableMapper classTimeTableMapper;

    private Map<Integer, Set<Integer>> classes_timetable = new HashMap<>();
    private Map<String, int[]> subjectAndAcademy = new HashMap<>();

    public static void main(String[] args) {
        ExcelService excelService = new ExcelService();
        excelService.readExcel();
    }

    public void init(){
        List<Classes> classesList = classesMapper.selectByExample(new ClassesExample());
        if(classesList.size() == 0){
            throw new RuntimeException();
        }
        for (Classes classes : classesList) {
            subjectAndAcademy.put(classes.getName(), new int[]{classes.getSubject(), classes.getAcademy()});
        }
    }

    public Room parseBuilding(String location){
        int start = 0;
        int end = 0;
        int type = 0;
        for(int i = 0; i < location.length(); i++){
            if(isAlphabet(location.charAt(i))){
                start = i;
                end = i + 1;
                if(isAlphabet(location.charAt(end))) {
                    end++;
                }
                break;
            }
            if(isNumber(location.charAt(i))){
                start = end = i - 1;
                type = 1;
                break;
            }
        }
        if(type == 0){
            String direction = location.substring(start, end);
            int code = 0;
            switch (direction){
                case "N":
                    code = 1;break;
                case "S":
                    code = 2;break;
                case "E":
                    code = 3;break;
                case "W":
                    code = 4;break;
                case "EN":
                    code = 5;break;
                case "ES":
                    code = 6;break;
                case "WN":
                    code = 7;break;
                case "WS":
                    code = 8;break;
                default:
                    throw new IllegalArgumentException("no code match");
            }
            String building = location.substring(0, start);
            if(building.equals("科")) building = "科厦";
            int floorAndNumber = Integer.parseInt(location.substring(end, location.length()));
            int floor = floorAndNumber;
            if(floor >= 1000){
                floor /= 1000;
            } else {
                floor /= 100;
            }
            int number = floorAndNumber % 100;
            Room room = new Room();
            room.setArea(Building.getBuildingByName(building));
            room.setDirection(Direction.getDirectionByCode(code));
            room.setFloor(floor);
            room.setNumber(number);
            room.setName(location);
            room.setIsAllow((byte) 1);
            return room;
        }
        String building = location.substring(0, start);
        if(building.equals("科")) building = "科厦";
        int floorAndNumber = Integer.parseInt(location.substring(end + 1, location.length()));
        int floor = floorAndNumber / 100;
        int number = floorAndNumber % 100;
        Room room = new Room();
        room.setArea(Building.getBuildingByName(building));
        room.setDirection(Direction.getDirectionByCode(0));
        room.setFloor(floor);
        room.setNumber(number);
        room.setName(location);
        room.setIsAllow((byte) 1);
        return room;
    }

    public boolean isAlphabet(char a){
        return (a >= 'a' && a <= 'z') || (a >= 'A' && a <= 'Z');
    }

    public void insertDb(List<ExcelResult> excelResults){
        for(ExcelResult excelResult : excelResults){
            List<Classes> classesList = excelResult.getClassesList();
            if(classesList.size() == 0) continue;
            System.out.println("excelResult  " + excelResult);
            CourseExample example = new CourseExample();
            example.createCriteria()
                    .andUidEqualTo(excelResult.getCourse_id());
            List<Course> courses = courseMapper.selectByExample(example);
            Course course = null;
            Map<String, Integer> map = excelResult.getStartAndStopTime();
            if(courses.size() == 0){
                course = new Course();
                course.setName(excelResult.getCourse_name());
                course.setUid(excelResult.getCourse_id());
                course.setAcademy(Academy.getAcademyBySimpleName(excelResult.getAcademy()));
                course.setCredit(0);
                course.setType(CourseType.getCourseByByte(map.get("distinct")));
                System.out.println("course    " + course);
                courseMapper.insertSelective(course);
            }
            if(course == null){
                course = courses.get(0);
                System.out.println("course    " + course);
            }
            Set<Integer> timetableIds = new HashSet<>();
            RoomExample roomExample = new RoomExample();
            roomExample.createCriteria()
                    .andNameEqualTo(excelResult.getLocation());
            List<Room> rooms = roomMapper.selectByExample(roomExample);
            Room room = null;
            if(rooms.size() > 0){
                room = rooms.get(0);
                System.out.println("Room     " + room);
            } else {
                room = parseBuilding(excelResult.getLocation());
                System.out.println("Room     " + room);
                roomMapper.insertSelective(room);
            }
            if(map.size() == 5){
                for(int i = 1; i <= 2; i++){
                    CourseTimeTable courseTimeTable = new CourseTimeTable();
                    courseTimeTable.setTerm(2);
                    courseTimeTable.setYear(2019);
                    courseTimeTable.setCourse(course);
                    courseTimeTable.setWeek(excelResult.getWeek());
                    courseTimeTable.setOrder(excelResult.getOrder());
                    courseTimeTable.setDistinct(excelResult.getStartAndStopTime().get("distinct"));
                    courseTimeTable.setRoom(room);
                    if(i == 2){
                        courseTimeTable.setStart(excelResult.getStartAndStopTime().get("start2"));
                        courseTimeTable.setEnd(excelResult.getStartAndStopTime().get("end2"));
                    } else {
                        courseTimeTable.setStart(excelResult.getStartAndStopTime().get("start"));
                        courseTimeTable.setEnd(excelResult.getStartAndStopTime().get("end"));
                    }
                    System.out.println("courseTimetable     " + courseTimeTable);
                    courseTimeTableMapper.insertSelective(courseTimeTable);
                    timetableIds.add(courseTimeTable.getId());

                }
            } else {
                CourseTimeTable courseTimeTable = new CourseTimeTable();
                courseTimeTable.setTerm(2);
                courseTimeTable.setYear(2019);
                courseTimeTable.setCourse(course);
                courseTimeTable.setWeek(excelResult.getWeek());
                courseTimeTable.setOrder(excelResult.getOrder());
                courseTimeTable.setRoom(room);
                courseTimeTable.setDistinct(excelResult.getStartAndStopTime().get("distinct"));
                courseTimeTable.setStart(excelResult.getStartAndStopTime().get("start"));
                courseTimeTable.setEnd(excelResult.getStartAndStopTime().get("end"));
                System.out.println("courseTimetable     " + courseTimeTable);
                courseTimeTableMapper.insertSelective(courseTimeTable);
                timetableIds.add(courseTimeTable.getId());
            }
            for(int i = 0; i < classesList.size(); i++){
                Classes classes = classesList.get(i);
                if(Objects.equals("", classes.getClassname()) || Objects.isNull(classes.getClassname()))continue;
                if(Objects.isNull(classes.getId())){
                    ClassesExample classesExample = new ClassesExample();
                    classesExample.createCriteria()
                            .andYearEqualTo(classes.getYear())
                            .andNumEqualTo(classes.getNum())
                            .andNameEqualTo(classes.getName());
                    List<Classes> cs = classesMapper.selectByExample(classesExample);
                    System.out.println("classes     " + classes);
                    if(cs.size() == 0){
                        classesMapper.insertSelective(classes);
                    }
                }
                Set<Integer> ids = classes_timetable.get(classes.getId());
                if(ids == null){
                    classes_timetable.put(classes.getId(), timetableIds);
                } else {
                    ids.addAll(timetableIds);
                    classes_timetable.put(classes.getId(), ids);
                }
            }
        }
        for(Map.Entry<Integer, Set<Integer>> entry : classes_timetable.entrySet()){
            Set<Integer> set = entry.getValue();
            for(Integer id : set){
                System.out.println("clas_timetable   clas_id" + entry.getKey() + "      timetable_id" + id);
                if(Objects.isNull(entry.getKey())) continue;
                classTimeTableMapper.insert(entry.getKey(), id);
            }
        }
    }

    public  void readExcel(){
        init();
        FileInputStream fis=null;
        Workbook workbook=null;
        try {
            fis=new FileInputStream("C:\\Users\\Yuki\\Desktop\\18-19-2全校大课表 - 副本.xls");
            workbook = new HSSFWorkbook(fis);
            Sheet sheet=workbook.getSheetAt(0);
            int rowNum=sheet.getLastRowNum();
            List<ExcelResult> excelResults = new ArrayList<>();
            for(int i =1;i<rowNum;i++){
                Row row=sheet.getRow(i);
                ExcelResult excelResult = new ExcelResult();
                if(i == 422){
                    Map map = new HashMap();
                }
                excelResult.setId((int) Double.parseDouble(String.valueOf(row.getCell(0)).trim()));
                excelResult.setCourse_id(String.valueOf(row.getCell(1)).trim());
                excelResult.setCourse_num((int) Double.parseDouble(String.valueOf(row.getCell(2)).trim()));
                excelResult.setCourse_name(String.valueOf(row.getCell(3)).trim());
                excelResult.setT_name(String.valueOf(row.getCell(4)).trim());
                excelResult.setClassesList(parseClassname(String.valueOf(row.getCell(5)).trim(), String.valueOf(row.getCell(6)).trim()));
                excelResult.setAcademy(String.valueOf(row.getCell(6)).trim());
                excelResult.setStartAndStopTime(getStartAndStopTime(String.valueOf(row.getCell(7)).trim()));
                excelResult.setWeek((int) Double.parseDouble(String.valueOf(row.getCell(8)).trim()));
                excelResult.setOrder((int) Double.parseDouble(String.valueOf(row.getCell(9)).trim()));
                excelResult.setLocation(String.valueOf(row.getCell(11)).trim());
                System.out.println(excelResult);
                excelResults.add(excelResult);
            }
//            HashMap hashMap = new HashMap();
            insertDb(excelResults);
        }catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public Map<String, Integer> getStartAndStopTime(String str){
        String[] strs = str.split(",");
        Map<String, Integer> map = new HashMap<>();
        if(strs.length == 1){
            getWeekMap(strs[0], map);
            return map;
        }
        String[] week = strs[0].split("-");
        if(week.length == 1){
            map.put("start2", Integer.parseInt(week[0]));
            map.put("end2", Integer.parseInt(week[0]));
        } else {
            map.put("start2", Integer.parseInt(week[0]));
            map.put("end2", Integer.parseInt(week[1]));
        }
        getWeekMap(strs[1], map);
        return map;
    }

    public void getWeekMap(String string, Map<String, Integer> original){
        if(string.split("-").length == 1){
            int index = 0;
            for(int i = 0; i < string.length(); i++){
                if(isNumber(string.charAt(i))){
                    index++;
                } else {
                    original.put("start", Integer.parseInt(string.substring(0, index)));
                    original.put("end", Integer.parseInt(string.substring(0, index)));
                }
                if(Objects.equals(string.charAt(i), '单')){
                    original.put("distinct", 1);
                } else if(Objects.equals(string.charAt(i), '双')){
                    original.put("distinct", 2);
                }
            }
            original.put("distinct", 0);
            return;
        }
        int index = 0;
        int count = 1;
        int flag = 1;
        for(int i = 0; i < string.length(); i++){
            if(isNumber(string.charAt(i))){
                index++;
            } else {
                if(count == 1){
                    original.put("start", Integer.parseInt(string.substring(0, index)));
                    count = index + 1;
                } else if(flag == 1){
                    original.put("end", Integer.parseInt(string.substring(count, index + 1)));
                    flag = 0;
                }
            }
            if(Objects.equals(string.charAt(i), '单')){
                original.put("distinct", 1);
            } else if(Objects.equals(string.charAt(i), '双')){
                original.put("distinct", 2);
            }
        }
        original.put("distinct", 0);
    }

    public List<Classes> parseClassname(String name, String academy){
        if(Objects.isNull(name) || Objects.equals("", name)){
            return new ArrayList<>();
        }
        if(name.startsWith("财会S")){
            String year = name.substring(3, name.length());
            ClassesExample example = new ClassesExample();
            example.createCriteria()
                    .andNameEqualTo(name);
            List<Classes> classesList = classesMapper.selectByExample(example);
            if(classesList.size() > 0){
                List<Classes> result = new ArrayList<>();
                result.add(classesList.get(0));
                return result;
            }
        } else if(name.equals("全校16俄语生") || name.equals("全校18俄语生")){
            return new ArrayList<>();
        } else if(name .equals("全校18日语生")){
            return new ArrayList<>();
        } else if(name.equals("16生选课")){
            return new ArrayList<>();
        } else if(name.equals("全校17生") || name.equals("全校18生")){
            return new ArrayList<>();
        }
        String[] classes = name.split(" ");
        List<Classes> classesList = new ArrayList<>();
        for(String classname : classes){
            if(classname.endsWith("班")){
                classname = classname.substring(0, classname.length() -1);
            }
            if(classname.startsWith("财会S")){
                ClassesExample example = new ClassesExample();
                example.createCriteria()
                        .andNameEqualTo(classname);
                List<Classes> classesList1 = classesMapper.selectByExample(example);
                if(classesList1.size() > 0){
                    classesList.add(classesList1.get(0));
                }
                continue;
            }
            String[] str = classname.split("-");
            String[] majorAndYear = getMajorAndYear(str[0]);
            int start = Integer.valueOf(str[1]);
            int end = Integer.valueOf(str[str.length - 1]);
            for(int j = start; j <= end; j++){
                ClassesExample example = new ClassesExample();
                example.createCriteria()
                        .andNumEqualTo(j)
                        .andYearEqualTo(Integer.parseInt(majorAndYear[1]))
                        .andNameEqualTo(majorAndYear[0]);
                List<Classes> classesList1 = classesMapper.selectByExample(example);
                if(classesList1.size() > 0){
                    classesList.add(classesList1.get(0));
                } else {
                    Classes classes1 = new Classes();
                    classes1.setName(majorAndYear[0]);
                    classes1.setNum(j);
                    classes1.setSubject(subjectAndAcademy.get(majorAndYear[0])[0]);
                    classes1.setYear(Integer.parseInt(majorAndYear[1]));
                    classes1.setAcademy(subjectAndAcademy.get(majorAndYear[0])[1]);
                    classesList.add(classes1);
                }
            }
        }
        return classesList;
    }

    public String[] getMajorAndYear(String str){
        for(int i = 0; i < str.length(); i++){
            if(isNumber(str.charAt(i))) {
                return new String[]{str.substring(0, i), str.substring(i, str.length())};
            }
        }
        return null;
    }

    public boolean isNumber(char a){
        return '0' <= a && a <= '9';
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


