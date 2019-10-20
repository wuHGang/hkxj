package cn.hkxj.platform.spider.newmodel.searchclassroom;

import cn.hkxj.platform.pojo.UrpClassroom;
import lombok.Data;

@Data
public class SearchClassroomResult {
    private Integer classNumberOfSeats;

    private String departmentName;

    private String campusName;

    private String zxjxjhh;

    private String classroomName;

    private String executiveEducationPlanName;

    private String teachingBuildingName;

    private Id id;

    private String classroomTypeDirections;

    @Data
    public class Id
    {
        private String executiveEducationPlanNumber;

        private String campusNumber;

        private String teachingBuildingNumber;

        private String classroomNumber;

    }

    public UrpClassroom transToUrpClassRoom(){
        return new UrpClassroom()
                .setName(classroomName)
                .setNumber(id.classroomNumber)
                .setTeachingBuildingNumber(id.teachingBuildingNumber)
                .setTeachingBuildingName(teachingBuildingName)
                .setCampusNumber(id.campusNumber)
                .setCampusName(campusName)
                .setSeats(classNumberOfSeats)
                .setType(classroomTypeDirections)
                .setDepartment(departmentName);
    }
}
