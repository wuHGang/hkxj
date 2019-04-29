package cn.hkxj.platform.mapper;

import cn.hkxj.platform.pojo.SubscribeGradeUpdate;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface SubscribeGradeUpdateMapper {
    String isOpenidSubscribed(String openid);

    int insert(SubscribeGradeUpdate subscribeGradeUpdate);

    List<SubscribeGradeUpdate> getSubscribedList();


}
