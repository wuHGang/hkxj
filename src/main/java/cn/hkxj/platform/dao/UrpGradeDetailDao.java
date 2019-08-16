package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpGradeDetailMapper;
import cn.hkxj.platform.pojo.UrpGrade;
import cn.hkxj.platform.pojo.UrpGradeDetail;
import cn.hkxj.platform.pojo.example.UrpGradeDetailExample;
import cn.hkxj.platform.spider.newmodel.UrpGradeForSpider;
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

    public List<UrpGradeDetail> insertForSpider(UrpGradeForSpider urpGradeForSpider, UrpGrade urpGrade){
        List<UrpGradeDetail> urpGradeDetailList = urpGradeForSpider.getUrpGradeDetailForSpider().convertToUrpGradeDetail();
        //urpGradeDetailList为空的情况就是成绩没有明细
        if(urpGradeDetailList == null) return null;
        //因为convertToUrpGradeDetail返回的UrpGradeDetail中都是没有gradeId的，所以需要设置后才进行成绩详情的插入
        urpGradeDetailList.stream().peek(gradeDetail -> gradeDetail.setGradeId(urpGrade.getId()))
                .forEach(this::insertUrpGradeDetail);
        return urpGradeDetailList;
    }

    public List<UrpGradeDetail> getUrpGradeDetail(int gradeId){
        UrpGradeDetailExample example = new UrpGradeDetailExample();
        example.createCriteria()
                .andGradeIdEqualTo(gradeId);
        return urpGradeDetailMapper.selectByExample(example);
    }
}
