package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
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

}