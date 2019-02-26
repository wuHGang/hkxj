package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.CETStudent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CETMapper {

    int insertCET(CETStudent cetStudent);

    CETStudent getCETStudentByAccount(int account);
}
