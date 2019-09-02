package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.RoomMapper;
import cn.hkxj.platform.pojo.Room;
import cn.hkxj.platform.pojo.example.RoomExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author Yuki
 * @date 2019/8/30 10:56
 */
@Service
public class RoomDao {

    @Resource
    private RoomMapper roomMapper;

    public boolean ifExistRoom(String classroomName) {
        RoomExample example = new RoomExample();
        example.createCriteria()
                .andNameEqualTo(classroomName);
        return roomMapper.selectByExample(example).size() == 1;
    }

    public void insertRoom(Room room) {
        roomMapper.insertSelective(room);
    }

    public Room getRoomByName(String classroomName){
        RoomExample example = new RoomExample();
        example.createCriteria()
                .andNameEqualTo(classroomName);
        return roomMapper.selectByExample(example).stream().findFirst().orElse(null);
    }

    public Room getRoomById(int id){
        return roomMapper.selectByPrimaryKey(id);
    }

    public Room saveOrGetRoomFromDb(Room room) {
        //如果不存在相应的教学计划，就先插入再返回
        if (!ifExistRoom(room.getName())) {
            insertRoom(room);
        } else {
            //如果存在从数据库中获取后返回
            room = getRoomByName(room.getName());
        }
        return room;
    }
}
