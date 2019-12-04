package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.UrpClassroomMapper;
import cn.hkxj.platform.pojo.UrpClassroom;
import cn.hkxj.platform.pojo.example.UrpClassroomExample;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UrpClassRoomDao {
    @Resource
    private UrpClassroomMapper urpClassroomMapper;

    public List<UrpClassroom> getAllClassroom(){
        return selectByClassroom(new UrpClassroom());
    }

    public List<UrpClassroom> selectByClassroom(UrpClassroom urpClassroom){
        UrpClassroomExample urpClassroomExample = new UrpClassroomExample();
        UrpClassroomExample.Criteria criteria = urpClassroomExample.createCriteria();

        if(urpClassroom.getNumber() != null){
            criteria.andNumberEqualTo(urpClassroom.getNumber());
        }

        if(StringUtils.isNotEmpty(urpClassroom.getName())){
            criteria.andNameEqualTo(urpClassroom.getName());
        }

        return urpClassroomMapper.selectByExample(urpClassroomExample);
    }

    public void insertSelective(UrpClassroom urpClassroom){
        urpClassroomMapper.insertSelective(urpClassroom);
    }


    public UrpClassroom selectByNumber(String number){
        return selectByClassroom(new UrpClassroom().setNumber(number)).stream().findFirst().get();
    }

    public UrpClassroom selectByName(String name){
        return selectByClassroom(new UrpClassroom().setName(name)).stream().findFirst().orElse(null);
    }
}
