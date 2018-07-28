package cn.hkxj.platform.service.wechat.common.exam;

import cn.hkxj.platform.pojo.Exam;
import cn.hkxj.platform.pojo.Wechatuser;

import java.io.IOException;
import java.util.List;

/**
 * @author JR Chan
 * @date 2018/6/13 10:39
 */
public interface ExamService {

    /**
     * 通过用户的班级名获取对应的课程列表，如果数据库中没有对应班级的课程列表
     * 就先通过爬虫爬取相应的数据，然后插入到数据库中。
     * @param wechatuser
     * @return
     * @throws IOException
     */
    List<Exam> getListByClassname(Wechatuser wechatuser) throws IOException;

    /**
     * 将包含有考试安排的实体列表转换成一段文本
     * @param exams
     * @return
     */
    String toText(List<Exam> exams);
}
