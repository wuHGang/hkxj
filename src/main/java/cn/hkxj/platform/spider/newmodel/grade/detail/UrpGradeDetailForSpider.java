package cn.hkxj.platform.spider.newmodel.grade.detail;

import cn.hkxj.platform.pojo.UrpGradeDetail;
import cn.hkxj.platform.spider.newmodel.grade.detail.MxGradeDetail;
import cn.hkxj.platform.spider.newmodel.grade.detail.XsGradeDetail;
import com.google.common.collect.Lists;
import lombok.Data;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author Yuki
 * @date 2019/7/31 19:52
 */
@Data
public class UrpGradeDetailForSpider {
    /**
     * 总共有3个元素，第一个是平时成绩相关系数，第二个是实验成绩相关系数，第三个是实践成绩相关系数
     */
    private List<XsGradeDetail> xs;
    /**
     * 总共有3个元素，第一个是平时成绩，第二个是实验成绩，第三个是实践成绩
     */
    private List<MxGradeDetail> mx;
    /**
     * 舍入
     */
    private String rounding;

    public List<UrpGradeDetail> convertToUrpGradeDetail(){
        List<UrpGradeDetail> urpGradeDetailList = Lists.newArrayList();
        if(CollectionUtils.isEmpty(xs) || CollectionUtils.isEmpty(mx)){
            return Collections.emptyList();
        }
        //因为xs和mx都只有3个元素，所以直接for循环
        for(int i = 0; i < 3; i ++){
            XsGradeDetail xsGradeDetail = this.xs.get(i);
            MxGradeDetail mxGradeDetail = this.mx.get(i);
            UrpGradeDetail urpGradeDetail = new UrpGradeDetail();
            urpGradeDetail.setUsualScoreCoefficient(xsGradeDetail.getUsualScoreCoefficient());
            urpGradeDetail.setMidtermScoreCoefficient(xsGradeDetail.getMidtermScoreCoefficient());
            urpGradeDetail.setEndtermScoreCoefficient(xsGradeDetail.getEndtermScoreCoefficient());
            urpGradeDetail.setScoreCoefficient(xsGradeDetail.getScoreCoefficient());
            urpGradeDetail.setXsRemark(xsGradeDetail.getRemark());
            urpGradeDetail.setScoreTypeCode(xsGradeDetail.getId().getScoreTypeCode());
            //cjxs为first,cjxs_为second，first和second只是为了区分，没有特殊含义
            urpGradeDetail.setFirstcjxs(mxGradeDetail.getFirstcjxs());
            urpGradeDetail.setFirstpscjxs(mxGradeDetail.getFirstpscjxs());
            urpGradeDetail.setFirstqmcjxs(mxGradeDetail.getFirstqmcjxs());
            urpGradeDetail.setFirstqzcjxs(mxGradeDetail.getFirstqzcjxs());
            urpGradeDetail.setSecondcjxs(mxGradeDetail.getSecondcjxs());
            urpGradeDetail.setSecondpscjxs(mxGradeDetail.getSecondpscjxs());
            urpGradeDetail.setSecondqmcjxs(mxGradeDetail.getSecondqmcjxs());
            urpGradeDetail.setSecondqzcjxs(mxGradeDetail.getSecondqzcjxs());
            urpGradeDetail.setMxRemark(mxGradeDetail.getRemark());
            urpGradeDetail.setMxRemark1(mxGradeDetail.getRemark1());
            urpGradeDetail.setMxRemark2(mxGradeDetail.getRemark2());
            urpGradeDetail.setTotalGrade(mxGradeDetail.getZcj());
            urpGradeDetail.setMidtermGrade(mxGradeDetail.getQzcj());
            urpGradeDetail.setFinaltermGrade(mxGradeDetail.getQmcj());
            urpGradeDetail.setRegularGrade(mxGradeDetail.getPscj());
            urpGradeDetailList.add(urpGradeDetail);
        }
        return urpGradeDetailList;
    }
}
