package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

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

    public String getTermCode(){
        return this.getStartYear() + "-" + this.getEndYear();
    }

    public String getTermName(){
        if(this.order == 1){
            return "第一学期";
        } else if (this.order == 2){
            return "第二学期";
        } else {
            return "未知学期";
        }
    }

    public String getTermYear(){
        return this.startYear + "-" + this.endYear;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        return new EqualsBuilder()
                .append(startYear, term.startYear)
                .append(endYear, term.endYear)
                .append(order, term.order)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(startYear)
                .append(endYear)
                .append(order)
                .toHashCode();
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
