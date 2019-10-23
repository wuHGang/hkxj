package cn.hkxj.platform.elasticsearch.document;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
@Document(indexName = "school",type = "courseTimeTable")
public class CourseTimeTableDocument implements Serializable {
    private Long id;

    private String courseName;

    private String courseId;

    private String courseOrder;

    private List<String> teacherNameList;

    private List<String> teacherAccountList;

    private List<String> classNameList;

    private List<String> classNumList;

    private String classRoomName;

    private String classRoomNumber;

    private int startWeek;

    private int endWeek;

    private String weekDescription;

    private String termYear;

    private int termOrder;

    private int classDay;

    private int classOrder;

    private String academyName;

    private List<String> subjectNameList;
}
