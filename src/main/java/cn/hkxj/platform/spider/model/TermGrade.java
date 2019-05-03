package cn.hkxj.platform.spider.model;

import cn.hkxj.platform.pojo.Grade;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.List;

/**
 * @author junrong.chen
 * @date 2019/4/26
 */
public class TermGrade {
    private List<UrpGrade> gradeList;
    private String term;

    public List<UrpGrade> getGradeList() {
        return gradeList;
    }

    public void setGradeList(List<UrpGrade> gradeList) {
        this.gradeList = gradeList;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("gradeList", gradeList)
                .add("term", term)
                .toString();
    }
}
