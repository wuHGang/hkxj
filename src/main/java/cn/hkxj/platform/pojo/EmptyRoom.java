package cn.hkxj.platform.pojo;

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
    private List<Integer> orderList = new ArrayList<>();

    public EmptyRoom(String name) {
        this.name = name;
    }

    public void addOrder(Integer order) {
        orderList.add(order);
    }
}
