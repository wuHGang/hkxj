package cn.hkxj.platform.exceptions;

/**
 * 针对问卷未填写
 *
 * @author JR Chan
 * @date 2018/12/12
 */
public class FormNotFillException extends SpiderException {
    public FormNotFillException(int account) {
        super(String.valueOf(account) + " 问卷未填写");
    }
}
