package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.UrpClassroom;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class EmptyRoomVo {
    private UrpClassroom urpClassroom;
    // orderList存储该教室的空课节次
    private List<Integer> orderList;
}
