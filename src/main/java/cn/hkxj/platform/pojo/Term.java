package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;

/**
 * @author JR Chan
 * @date 2019/5/1
 */
public class Term {
    /**
     * 如果是2018到2019年第一学期
     * startYear = 2018
     * endYear = 2019
     * order = 1
     */
    private int startYear;
    private int endYear;
    private int order;

    public Term() {

    }

    public Term(int startYear, int endYear, int order) {
        this.startYear = startYear;
        this.endYear = endYear;
        this.order = order;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("startYear", startYear)
                .add("endYear", endYear)
                .add("order", order)
                .toString();
    }
}
