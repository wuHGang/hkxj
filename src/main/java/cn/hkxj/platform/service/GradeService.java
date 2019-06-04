package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.WebResponse;

public interface GradeService {
    WebResponse findGradeByUser(Integer account, String password);
}
