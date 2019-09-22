package cn.hkxj.platform.spider.newmodel.emptyroom;

import lombok.Data;

/**
 * @author Syaeldon
 */
@Data
public class EmptyRoomRecord {
    private String campusName;
    private int classNumberOfSeats;
    private String classroomName;
    private String classroomStatusCode;
    private String classroomStatusName;
    private String classroomTypeCode;
    private String classroomTypeName;
    private String departmentNum;
    private int examNumberOfSeats;
    private EmptyRoomId id;
    private String isTrueClassroom;
    private String remark;
    private String szlc;
    private String teachingBuildingName;
    private String timetablePriority;
    private String timetablePropertyCode;
}
