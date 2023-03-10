package org.evolboot.sms.domain;

import org.evolboot.shared.sms.MessageTag;
import org.evolboot.shared.sms.SmsSendChannel;
import lombok.Builder;
import lombok.Getter;

/**
 * @author evol
 */
public interface SmsSender {

    /**
     * 发送验证码
     *
     * @param messageTag   短信类型
     * @param mobilePrefix 手机号前缀
     * @param mobile       手机号
     * @param content      短信内容
     * @return
     */
    Response sendSms(MessageTag messageTag, String mobilePrefix, String mobile, String content);

    @Builder
    @Getter
    class Response {
        boolean sendStatus;
        String smsContent;
    }

    /**
     * 短信渠道
     *
     * @return
     */
    SmsSendChannel getSmsSendChannel();
}
