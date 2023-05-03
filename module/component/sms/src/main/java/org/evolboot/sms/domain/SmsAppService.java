package org.evolboot.sms.domain;

import org.evolboot.shared.sms.SmsMessageTag;

/**
 * @author evol
 */
public interface SmsAppService {


    /**
     * 发送短信
     *
     * @param messageTag   短信类型
     * @param mobilePrefix 手机号前缀
     * @param mobile       手机号
     * @param params       参数
     * @return
     */
    SmsSender.Response sendSms(SmsMessageTag messageTag, String mobilePrefix, String mobile, String... params);

}
