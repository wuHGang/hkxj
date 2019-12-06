package cn.hkxj.platform.service;

import cn.hkxj.platform.pojo.*;
import cn.hkxj.platform.spider.newmodel.grade.CurrentGrade;
import cn.hkxj.platform.spider.newmodel.grade.general.UrpGeneralGradeForSpider;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GradeService {

    @Resource
    private NewUrpSpiderService newUrpSpiderService;


}
