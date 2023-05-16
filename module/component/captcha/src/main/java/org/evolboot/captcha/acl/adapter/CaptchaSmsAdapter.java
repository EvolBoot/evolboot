package org.evolboot.captcha.acl.adapter;

import org.evolboot.captcha.acl.client.CaptchaSmsClient;
import org.evolboot.shared.sms.SmsMessageTag;
import org.evolboot.sms.domain.SmsAppService;
import org.evolboot.sms.domain.SmsSender;
import org.springframework.stereotype.Service;

/**
 * @author evol
 */
@Service
public class CaptchaSmsAdapter implements CaptchaSmsClient {

    private final SmsAppService smsAppService;

    public CaptchaSmsAdapter(SmsAppService smsAppService) {
        this.smsAppService = smsAppService;
    }

    @Override
    public SmsSender.Response sendSms(SmsMessageTag messageTag, String mobilePrefix, String mobile, String... params) {
        return smsAppService.sendSms(messageTag, mobilePrefix, mobile, params);
    }
}
