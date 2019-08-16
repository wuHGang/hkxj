package cn.hkxj.platform.pojo;

import java.util.Date;

public class Major {
    private Integer id;

    private String zyh;

    private String zym;

    private Date gmtCreate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getZyh() {
        return zyh;
    }

    public void setZyh(String zyh) {
        this.zyh = zyh == null ? null : zyh.trim();
    }

    public String getZym() {
        return zym;
    }

    public void setZym(String zym) {
        this.zym = zym == null ? null : zym.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }
}