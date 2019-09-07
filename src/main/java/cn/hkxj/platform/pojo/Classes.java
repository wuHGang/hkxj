package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;

import java.util.List;

@Data
public class Classes {
    private Integer id;

    private String name;

    private Integer academy;

    private Integer subject;

    private Integer year;

    private Integer num;

    private List<Integer> courseTimeTableIds;


    public String getClassname(){
        return this.getName() + this.getYear() + "-" + this.getNum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Classes classes = (Classes) o;
        return
                Objects.equal(name, classes.name) &&
                Objects.equal(academy, classes.academy) &&
                Objects.equal(subject, classes.subject) &&
                Objects.equal(year, classes.year) &&
                Objects.equal(num, classes.num);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), id, name, academy, subject, year, num, courseTimeTableIds);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("academy", academy)
                .add("subject", subject)
                .add("year", year)
                .add("num", num)
                .add("courseTimeTableIds", courseTimeTableIds)
                .toString();
    }
}