package cn.hkxj.platform.spider.newmodel.emptyroom;

import lombok.Data;

/**
 * @author Syaeldon
 */
@Data
public class EmptyRoomPost {
    private String weeks;
    private String executiveEducationPlanNumber;
    /**
     * codeCampusListNumber为校区编号
     * 01 主校区
     */
    private String codeCampusListNumber;
    /**
     * teaNum为教学楼编号
     * 01  主楼(东楼)
     * 02  科大
     * 03  主楼(西楼)
     * 04  操场
     * 05  实验楼
     * 06  图书馆
     * 110  管理学院
     * 111  松花四季
     */
    private String teaNum;
    /**
     * wSection为星期和节次
     * 星期数/节次
     */
    private String wSection;
    private String pageNum;
    private String pageSize;

    public EmptyRoomPost(String weeks, String teaNum, String wSection, String pageNum, String pageSize) {
        this.weeks = weeks;
        this.teaNum = teaNum;
        this.wSection = wSection;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public static String getBuildingCode(String building) {
        switch (building) {
            case "主楼东":
                return "01";
            case "科厦":
                return "02";
            case "主楼西":
                return "03";
            default:
                throw new IllegalArgumentException("never happen");
        }
    }
}
