package cn.hkxj.platform.service.impl;

import cn.hkxj.platform.mapper.GradeVOMapper;
import cn.hkxj.platform.pojo.WebResponse;
import cn.hkxj.platform.pojo.vo.GradeVO;
import cn.hkxj.platform.service.GradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GradeServiceImpl implements GradeService {
    @Autowired
    private GradeVOMapper gradeVOMapper;
    @Override
    public WebResponse findGradeByUser(Integer account, String password) {

            List<GradeVO> gradeVOList = gradeVOMapper.findGradeByUser(account,password);
            if (!(gradeVOList.size() > 0))
               return WebResponse.success().setMessage("没有查询到数据");
           return WebResponse.success(gradeVOList).setMessage("查询成功");


    }
}
