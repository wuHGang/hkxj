package cn.hkxj.platform.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UrpClassroom {
    private Integer id;

    private String number;

    private String name = "";

    private String campusName;

    private String campusNumber;

    private Integer seats = 0;

    private String teachingBuildingName;

    private String teachingBuildingNumber;

    private String department = "";

    private Integer floor = 0;

    private String type = "";

}