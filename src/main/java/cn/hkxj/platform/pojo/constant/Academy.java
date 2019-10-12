package cn.hkxj.platform.pojo.constant;

import com.alibaba.fastjson.JSON;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Maps;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/10/10 20:00
 */
public enum Academy {
    /**
     * 学院枚举与数据库相关code的映射
     */
    Unknown("未知", "未知", -1, -1),
    Mining("矿业工程学院", "矿业学院", 0, 1),
    Environmental("环境与化工学院", "环化学院", 1, 2),
	Safe("安全工程学院", "安全学院", 2, 3),
	electrical("电气与控制工程学院", "电气学院", 3,4),
    Electronics("电子与信息工程学院", "电信学院", 4, 5),
	Mechanical("机械工程学院", "机械学院", 5, 6),
	Economy("经济学院", "经济学院", 6, 11),
    Management("管理学院", "管理学院", 7, 10),
	Architecture("建筑工程学院", "建筑学院", 8, 8),
	humanity("人文社会科学学院", "人文学院", 9, 12),
    Marx("马克思主义学院", "马克思学院", 10, 13),
	Computer("计算机与信息工程学院", "计算机学院", 11, 9),
	Material("材料科学与工程学院", "材料学院", 12, 7),
    Science("理学院", "理学院", 13, 15),
	ForeignLanguage("外国语学院", "外国语学院", 14, 17),
	Building("国际教育学院", "国际学院", 15, 14),
	recruitment("招生与就业工作处", "招就处", 17, 40),
	TrainingCenter("工程训练与基础实验中心", "实训中心", 18, 19),
    StudentsAffairsDivision("学生处", "学生处", 19, 39),
    office("机关","机关",20, 34),
    sport("体育部", "体育部", 21, 18),
    Guard("保卫处", "保卫处", 22, 44),
    Library("图书馆","图书馆",24, 80),
    Web("网课","网课",25,-2),
    Postgraduate("研究生学院", "研究生学院", 26, 16),
    InnovationCenter("创新创业中心", "创新创业中心", 27, 20),

    YouthLeagueCommittee("团委", "团委", 28, 36),
    SocialServicesDivision("发展规划与社会服务处", "发展规划与社会服务处", 29, 37),
    AcademicAffairsOffice("教务处", "教务处", 30, 38),
    ManufacturingEngineeringCenter("现代制造工程中心", "现代制造工程中心", 31, 60),

    EvaluationCenter("评估中心", "评估中心", 32, 62),


    MiningResearchInstitute("矿业研究院", "矿业研究院", 33, 64),
    AnalyticalTestingResearchCenter("现代分析测试研究中心", "现代分析测试研究中心", 34, 77),

    NetworkCenter("信息网络中心", "信息网络中心", 35, 81),
    Hospital("医院","医院", 36, 84),
    Other("其它", "其它", 99, 99)
    ;

    private String academyName;

    private String  academySimpleName;

    private int academyCode;

    /**
     * 教务网对应的学院编号
     */
    private int urpCode;


    private Academy(String academyName, String academySimpleName, int academyCode, int urpCode){
        this.academyName = academyName;
        this.academyCode = academyCode;
        this.academySimpleName = academySimpleName;
        this.urpCode = urpCode;
    }

    public static String getNameByAcademyCode(int code){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyCode(), code)){
                return aca.getAcademyName();
            }
        }
        return null;
    }

    public static String getSimpleNameByCode(int code){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyCode(), code)){
                return aca.getAcademySimpleName();
            }
        }
        return null;
    }

    public static Integer getAcademyCodeBySimpleName(String simpleName){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademySimpleName(), simpleName)){
                return aca.getAcademyCode();
            }
        }
        return null;
    }

    public static Academy getAcademyByCode(int code){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyCode(), code)){
                return aca;
            }
        }
        return Unknown;
    }

    public static Integer getAcademyCodeByName(String name){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyName(), name)){
                return aca.getAcademyCode();
            }
        }
        throw new IllegalArgumentException("illegal academy name: "+name);
    }

    public static Academy getAcademyByName(String academyName) {
        for (Academy academy : Academy.values()) {
            if (Objects.equals(academy.getAcademyName(), academyName)) {
                return academy;
            }
        }
        return Unknown;
    }

    public static Academy getAcademyBySimpleName(String academySimpleName) {
        for (Academy academy : Academy.values()) {
            if (Objects.equals(academy.getAcademySimpleName(), academySimpleName)) {
                return academy;
            }
        }
        throw new IllegalArgumentException("illegal academy name: " + academySimpleName);
    }

    public String getAcademyName() {
        return academyName;
    }

    public String getAcademySimpleName() {
        return academySimpleName;
    }

    public int getAcademyCode() {
        return academyCode;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("academyName", academyName)
                .add("academySimpleName", academySimpleName)
                .add("academyCode", academyCode)
                .toString();
    }

    public static void main(String[] args) throws IOException {
        File file = new File("academy.json");
        HashMap<String, String> map = Maps.newHashMap();
        for (Academy academy : Academy.values()) {
            map.put(Integer.toString(academy.urpCode), academy.academyName);
        }

        Files.write(JSON.toJSONString(map).getBytes(), file);
    }


}
