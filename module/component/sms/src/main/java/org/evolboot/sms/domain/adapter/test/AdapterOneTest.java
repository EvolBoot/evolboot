package org.evolboot.sms.domain.adapter.test;

import org.evolboot.shared.sms.SmsMessageTag;
import org.evolboot.shared.sms.SmsSendChannel;
import org.evolboot.sms.domain.SmsSender;

/**
 * @author evol
 */
public class AdapterOneTest implements SmsSender {
    @Override
    public Response sendSms(SmsMessageTag messageTag, String mobilePrefix, String mobile, String content) {
        return null;
    }

    @Override
    public SmsSendChannel getSmsSendChannel() {
        return null;
    }
}
