package org.evolboot.captcha.acl.port;

import org.evolboot.shared.sms.MessageTag;
import org.evolboot.sms.domain.SmsSender;

/**
 * @author evol
 * 
 */
public interface CaptchaSmsPort {


    /**
     * 发送短信
     *
     * @param messageTag   短信类型
     * @param mobilePrefix 手机号前缀
     * @param mobile       手机号
     * @param params       参数
     * @return
     */
    SmsSender.Response sendSms(MessageTag messageTag, String mobilePrefix, String mobile, String... params);

}
