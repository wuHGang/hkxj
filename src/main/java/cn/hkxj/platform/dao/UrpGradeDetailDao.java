package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpGradeDetailMapper;
import cn.hkxj.platform.pojo.UrpGradeDetail;
import cn.hkxj.platform.pojo.example.UrpGradeDetailExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/8/14 22:26
 */
@Service
public class UrpGradeDetailDao {

    @Resource
    private UrpGradeDetailMapper urpGradeDetailMapper;

    public void insertUrpGradeDetail(UrpGradeDetail urpGradeDetail){
        urpGradeDetailMapper.insertSelective(urpGradeDetail);
    }

    public List<UrpGradeDetail> getUrpGradeDetail(int gradeId){
        UrpGradeDetailExample example = new UrpGradeDetailExample();
        example.createCriteria()
                .andGradeIdEqualTo(gradeId);
        return urpGradeDetailMapper.selectByExample(example);
    }

    /**
     * 将从爬虫爬取到的数据判断是需要存入数据库还是从数据库中进行获取
     * 因为成绩详情都是三个元素，所以返回一个集合
     * @param convertFromSpider 从爬虫爬取的信息中转化的成绩详情实体
     * @param urpGradeId 成绩编号
     * @return 成绩详情
     */
    public List<UrpGradeDetail> saveOrGetUrpGradeDetailFromDb(List<UrpGradeDetail> convertFromSpider, int urpGradeId){
        //urpGradeDetailList为空的情况就是成绩没有明细
        if(convertFromSpider == null) return null;
        //因为convertToUrpGradeDetail返回的UrpGradeDetail中都是没有gradeId的，所以需要设置后才进行成绩详情的插入
        convertFromSpider.stream().peek(gradeDetail -> gradeDetail.setGradeId(urpGradeId))
                .forEach(this::insertUrpGradeDetail);
        return convertFromSpider;
    }
}
