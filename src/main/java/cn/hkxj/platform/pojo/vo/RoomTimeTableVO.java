package cn.hkxj.platform.pojo.vo;

import cn.hkxj.platform.pojo.Room;
import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;

/**
 * @author JR Chan
 * @date 2019/6/14
 */
@Data
public class RoomTimeTableVO {
    private Room room;
    private List<RoomOrderTimeTableVO> roomOrderTimeTableList;

    public RoomTimeTableVO() {
        List<Integer> order = Lists.newArrayList(1, 3, 5, 7, 9);
        for (Integer integer : order) {
            RoomOrderTimeTableVO roomOrderTimeTableVO = new RoomOrderTimeTableVO();
            roomOrderTimeTableVO.setOrder(integer);
        }

    }
}
