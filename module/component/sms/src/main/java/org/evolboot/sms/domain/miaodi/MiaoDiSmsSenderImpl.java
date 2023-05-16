package org.evolboot.sms.domain.miaodi;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.shared.sms.SmsMessageTag;
import org.evolboot.shared.sms.SmsSendChannel;
import org.evolboot.sms.domain.SmsSender;
import org.springframework.stereotype.Component;


/**
 * 秒滴短信发送
 * 未完成,使用时需要重构
 *
 * @author evol
 */
@Slf4j
@Component
public class MiaoDiSmsSenderImpl implements SmsSender {

    private MiaoDiHttp miaoDiHttp;


    public void setMiaoDiHttp(MiaoDiHttp miaoDiHttp) {
        this.miaoDiHttp = miaoDiHttp;
    }


    @Override
    public Response sendSms(SmsMessageTag messageTag, String mobilePrefix, String mobile, String content) {
        log.info("秒滴验证码:{}", content);
        return null;
    }

    @Override
    public SmsSendChannel getSmsSendChannel() {
        return null;
    }
}
