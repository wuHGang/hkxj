package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

import java.util.Objects;

/**
 * @author Yuki
 * @date 2018/10/10 20:00
 */
public enum Academy {
    /**
     * 学院枚举与数据库相关code的映射
     */
    Environmental("环境与化工学院", "环化学院", 1),
	Safe("安全工程学院", "安全学院", 2),
	electrical("电气与控制工程学院", "电气学院", 3),
    Electronics("电子与信息工程学院", "电信学院", 4),
	Mechanical("机械工程学院", "机械学院", 5),
	Economy("经济学院", "经济学院", 6),
    Management("管理学院", "管理学院", 7),
	Architecture("建筑工程学院", "建筑学院", 8),
	humanity("人文社会科学学院", "人文学院", 9),
    Marx("马克思主义学院", "马克思学院", 10),
	Computer("计算机与信息工程学院", "计算机学院", 11),
	Material("材料科学与工程学院", "材料学院", 12),
    Science("理学院", "理学院", 13),
	ForeignLanguage("外国语学院", "外国语学院", 14),
	Building("国际教育学院", "国际学院", 15),
    Mining("矿业工程学院", "矿业学院", 0),
	recruitment("招就处", "招就处", 17),
	TrainingCenter("工程训练与基础实验中心", "实训中心", 18),
    StudentsAffairsDivision("学生处", "学生处", 19),
    office("机关","机关",20),
    sport("体育部","体育部",21);

    private String academyName;

    private String  academySimpleName;

    private int academyCode;


    private Academy(String academyName, String academySimpleName, int academyCode){
        this.academyName = academyName;
        this.academyCode = academyCode;
        this.academySimpleName = academySimpleName;
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

    public static Academy getAcademyByCode(int code){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyCode(), code)){
                return aca;
            }
        }
        throw new IllegalArgumentException("illegal academy code: "+code);
    }

    public static Integer getAcademyCodeByName(String name){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademyName(), name)){
                return aca.getAcademyCode();
            }
        }
        throw new IllegalArgumentException("illegal academy name: "+name);
    }

    public static Integer getCodeBySimpleName(String simpleName){
        for(Academy aca : Academy.values()){
            if(Objects.equals(aca.getAcademySimpleName(), simpleName)){
                return aca.getAcademyCode();
            }
        }
        throw new IllegalArgumentException("illegal academy simple name: "+simpleName);
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


}
