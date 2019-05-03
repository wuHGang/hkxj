package cn.hkxj.platform.pojo.constant;

/**
 * @author junrong.chen
 * @date 2018/11/28
 */
public enum  SubjectFirstProperty {
    /**
     * 课程第一属性和中文名
     */
    ENGINEERING("工学"),
    SCIENCE("理学"),
    MANAGEMENT("管理学"),
    LAW("法学"),
    ECONOMICS("经济学"),
    LITERATURE("文学");

    private String chinese;
    SubjectFirstProperty(String chinese) {
        this.chinese = chinese;
    }

    public String getChinese() {
        return chinese;
    }

    public void setChinese(String chinese) {
        this.chinese = chinese;
    }


    public static SubjectFirstProperty getPropertyByChinese(String chinese){
        switch (chinese){
            case "工学":
                return ENGINEERING;
            case "理学":
                return SCIENCE;
            case"管理学":
                return MANAGEMENT;
            default:
                throw new IllegalArgumentException(chinese);
        }
    }
}
