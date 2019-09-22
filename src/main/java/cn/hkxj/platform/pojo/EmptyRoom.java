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

    public EmptyRoom(String name) {
        this.name = name;
    }
}
