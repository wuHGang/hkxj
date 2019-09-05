package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
public class Student {
    private Integer id;

    private Integer account;

    private String password;

    private String name;

    private String sex;

    private String ethnic;

    private Classes classes;

    private Boolean isCorrect;

    private Date gmtCreate;

    private Date gmtModified;


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("account", account)
                .add("password", password)
                .add("name", name)
                .add("sex", sex)
                .add("ethnic", ethnic)
                .add("classes", classes)
                .add("isCorrect", isCorrect)
                .add("gmtCreate", gmtCreate)
                .add("gmtModified", gmtModified)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        return Objects.equal(this.id, that.id) &&
                Objects.equal(this.account, that.account) &&
                Objects.equal(this.password, that.password) &&
                Objects.equal(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, account, password, name, sex);
    }
}