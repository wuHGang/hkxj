package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
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