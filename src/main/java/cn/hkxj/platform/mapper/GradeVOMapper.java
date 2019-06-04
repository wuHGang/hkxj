package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.vo.GradeVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeVOMapper {

     List<GradeVO> findGradeByUser(@Param("account") Integer account, @Param("password") String password);
}
