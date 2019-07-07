package cn.hkxj.platform.config.wechat;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Yuki
 * @date 2019/6/17 22:15
 */
@Data
@ConfigurationProperties
public class WechatTemplateProperties {

    @Value("${wechat.mp.plus.templateId.course}")
    private String plusCourseTemplateId;

    @Value("${wechat.mp.plus.templateId.gradeUpdate}")
    private String plusGradeUpdateTemplateId;

    @Value("${wechat.mp.plus.templateId.tips}")
    private String plusTipsTemplateId;

    @Value("${wechat.mp.plus.templateId.exam}")
    private String plusExamTemplateId;

}
