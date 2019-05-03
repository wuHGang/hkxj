package cn.hkxj.platform.pojo.constant;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import lombok.Getter;

/**
 * @author zhouqinglai
 * @version version
 * @title StatisticsTypeEnum
 * @desc
 * @date: 2019年05月03日
 */
public enum StatisticsTypeEnum {

    /**
     * 接口统计
     */
    INTERFACE_STATISTICS(1, "interface_statistics");

    @Getter
    private int code;

    @Getter
    private String desc;

    private StatisticsTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final class Holder {
        public static final Map<Integer, StatisticsTypeEnum> TYPE_ENUM_MAP = Arrays.stream(values()).collect(Collectors.toMap(StatisticsTypeEnum::getCode, Function.identity()));
    }

    public static StatisticsTypeEnum of(int code) {
        return Holder.TYPE_ENUM_MAP.getOrDefault(code, null);
    }

    }
