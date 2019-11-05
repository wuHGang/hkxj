package cn.hkxj.platform.pojo;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Syaeldon
 */
@Data
public class EmptyRoom {
    private String name;

    // orderList存储该教室的空课节次
    private List<Integer> orderList = Lists.newArrayList(0,0,0,0,0);

    public EmptyRoom(String name) {
        this.name = name;
    }

    public void addOrder(Integer order) {
        orderList.set(order/2,1);
    }
}
