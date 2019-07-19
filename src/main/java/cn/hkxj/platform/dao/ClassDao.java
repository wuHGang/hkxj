package cn.hkxj.platform.dao;

import cn.hkxj.platform.mapper.ClassesMapper;
import cn.hkxj.platform.pojo.Classes;
import cn.hkxj.platform.pojo.example.ClassesExample;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author junrong.chen
 * @date 2019/7/19
 */
@Service
public class ClassDao {
    @Resource
    private ClassesMapper classesMapper;


    public List<Classes> getClassByClassName(Classes classes) {
        ClassesExample classesExample = new ClassesExample();
        classesExample.createCriteria()
                .andNameEqualTo(classes.getName())
                .andNumEqualTo(classes.getNum())
                .andYearEqualTo(classes.getYear());
        return classesMapper.selectByExample(classesExample);
    }

    public void insertClass(Classes classes){
        classesMapper.insert(classes);
    }

}
