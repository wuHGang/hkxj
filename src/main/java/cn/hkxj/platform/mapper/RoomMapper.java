package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.example.RoomExample;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoomMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Room record);

    int insertSelective(Room record);

    List<Room> selectByExample(RoomExample example);

    Room selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Room record);

    int updateByPrimaryKey(Room record);

    List<Integer> selectCourseTimeTableID(int roomId);

    Room selectByFuzzy(String name);
}