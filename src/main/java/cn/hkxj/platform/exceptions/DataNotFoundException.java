package cn.hkxj.platform.exceptions;

/**
 * @author JR Chan
 * @date 2018/12/12
 */
public class DataNotFoundException extends SpiderException {
    public DataNotFoundException(int account) {
        super(String.valueOf(account) + "暂时没有数据");
    }
}
