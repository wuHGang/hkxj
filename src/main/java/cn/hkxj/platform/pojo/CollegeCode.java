package cn.hkxj.platform.pojo;

/**
 * @author Yuki
 * @date 2018/10/10 20:00
 */
public enum CollegeCode {

    HR("环境与化工学院", "环化学院", 1), AQ("安全工程学院", "安全学院", 2), DQ("电气与控制工程学院", "电气学院", 3),
    DX("电子与信息工程学院", "电信学院", 4), JX("机械工程学院", "机械学院", 5), JJ("经济学院", "经济学院", 6),
    GL("管理学院", "管理学院", 7), JZ("建筑工程学院", "建筑学院", 8), RW("人文社会科学学院", "人文学院", 9),
    MKS("马克思主义学院", "马克思学院", 10), JSJ("计算机与信息工程学院", "计算机学院", 11), CL("材料科学与工程学院", "材料学院", 12),
    L("理学院", "理学院", 13), WGY("外国语学院", "外国语学院", 14), GJ("国际教育学院", "国际学院", 15),
    GY("矿业工程学院", "矿业学院", 16), ZJC("招就处", "招就处", 17), SXZX("实训中心", "实训中心", 18),
    XSC("学生处", "学生处", 19);

    private String collegeName;

    private String  collegeSimpleName;

    private Integer collegeCode;


    private CollegeCode(String collegeName, String collegeSimpleName, Integer collegeCode){
        this.collegeName = collegeName;
        this.collegeCode = collegeCode;
        this.collegeSimpleName = collegeSimpleName;
    }


    public String getCollegeName() {
        return collegeName;
    }

    public String getCollegeSimpleName() {
        return collegeSimpleName;
    }

    public Integer getCollegeCode() {
        return collegeCode;
    }

}
