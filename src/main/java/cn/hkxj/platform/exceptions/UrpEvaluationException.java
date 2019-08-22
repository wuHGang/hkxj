package cn.hkxj.platform.exceptions;

/**
 * @author junrong.chen
 * 学生评估未完成
 */
public class UrpEvaluationException extends RuntimeException{
    public UrpEvaluationException(String desc){
        super(desc);
    }
}
