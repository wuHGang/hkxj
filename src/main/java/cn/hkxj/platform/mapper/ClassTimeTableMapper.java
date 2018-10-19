package cn.hkxj.platform.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuki
 * @date 2018/10/19 21:03
 */
@Mapper
@Repository
public interface ClassTimeTableMapper {

    List<Integer> getTimeTableIdByClassId(Integer classId);
}
