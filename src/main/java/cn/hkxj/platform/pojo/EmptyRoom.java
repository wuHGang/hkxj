package cn.hkxj.platform.pojo;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class EmptyRoom {
    private String name;

    //orderList是该教室有课的节次集合
    private List<Integer> orderList;

    //orderList是该教室无课的节次集合
    private List<Integer> emptyOrderList;

    public void setOrderList(List<Integer> orderList){
        this.orderList=orderList;
        this.emptyOrderList=Arrays.asList(1,3,5,7,9);
        this.emptyOrderList.removeAll(orderList);
    }
}
