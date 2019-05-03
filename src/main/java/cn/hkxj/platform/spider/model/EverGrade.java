package cn.hkxj.platform.spider.model;


import com.google.common.base.MoreObjects;


import java.util.List;

/**
 * @author junrong.chen
 * @date 2019/4/26
 */
public class EverGrade {
    private List<TermGrade> everGrade;

    public List<TermGrade> getEverGrade() {
        return everGrade;
    }

    public void setEverGrade(List<TermGrade> everGrade) {
        this.everGrade = everGrade;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("everGrade", everGrade)
                .toString();
    }
}
