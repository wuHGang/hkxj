package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpClassroomMapper;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.pojo.example.UrpClassroomExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UrpClassRoomDao {
    @Resource
    private UrpClassroomMapper urpClassroomMapper;

    public List<UrpClassroom> getAllClassroom(){
        UrpClassroomExample urpClassroomExample = new UrpClassroomExample();

        return urpClassroomMapper.selectByExample(urpClassroomExample);
    }
}
