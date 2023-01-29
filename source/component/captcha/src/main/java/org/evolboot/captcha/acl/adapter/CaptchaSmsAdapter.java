package org.evolboot.captcha.acl.adapter;

import org.evolboot.captcha.acl.port.CaptchaSmsPort;
import org.evolboot.shared.sms.MessageTag;
import org.evolboot.sms.domain.SmsAppService;
import org.evolboot.sms.domain.SmsSender;
import org.springframework.stereotype.Service;

/**
 * @author evol
 * 
 */
@Service
public class CaptchaSmsAdapter implements CaptchaSmsPort {

    private final SmsAppService smsAppService;

    public CaptchaSmsAdapter(SmsAppService smsAppService) {
        this.smsAppService = smsAppService;
    }

    @Override
    public SmsSender.Response sendSms(MessageTag messageTag, String mobilePrefix, String mobile, String... params) {
        return smsAppService.sendSms(messageTag, mobilePrefix, mobile, params);
    }
}
