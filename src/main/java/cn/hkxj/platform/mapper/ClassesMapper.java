package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.ClassTimeTable;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.ClassesExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Yuki
 * @date 2018/10/11 18:58
 */
@Mapper
@Repository
public interface ClassesMapper {

    int countByExample(ClassesExample example);

    int deleteByExample(ClassesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Classes record);

    int insertSelective(Classes record);

    List<Classes> selectByExample(ClassesExample example);

    Classes selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Classes record, @Param("example") ClassesExample example);

    int updateByExample(@Param("record") Classes record, @Param("example") ClassesExample example);

    int updateByPrimaryKeySelective(Classes record);

    int updateByPrimaryKey(Classes record);

    List<ClassTimeTable> getClassesByTimetableIds(@Param("ids") List<Integer> ids);

    List<Classes> getClassesByIds(@Param("ids") List<Integer> ids);

}
