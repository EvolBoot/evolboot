package org.evolboot.sms.domain;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.configuration.domain.config.sms.SmsConfig;
import org.evolboot.configuration.domain.config.sms.SmsLocale;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.sms.SmsMessageTag;
import org.evolboot.shared.sms.SmsSendChannel;
import org.evolboot.sms.acl.client.SmsConfigClient;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author evol
 */
@Service
@Slf4j
public class SmsAppServiceImpl implements SmsAppService {

    private final SmsConfigClient smsConfigClient;

    private final Map<SmsSendChannel, SmsSender> smsSenders;

    public SmsAppServiceImpl(SmsConfigClient smsConfigClient, Map<SmsSendChannel, SmsSender> smsSenders) {
        this.smsConfigClient = smsConfigClient;
        this.smsSenders = smsSenders;
    }


    @Override
    public SmsSender.Response sendSms(SmsMessageTag messageTag, String mobilePrefix, String mobile, String... params) {
        SmsConfig smsConfig = smsConfigClient.getSmsConfig();
        Assert.isTrue(!ExtendObjects.isEmpty(smsConfig.getLocales()), "Please configure SMS.");
        String content = getSmsContent(smsConfig, messageTag, params);
        SmsSender smsSender = smsSenders.get(smsConfig.getChannel());
        return smsSender.sendSms(messageTag, mobilePrefix, mobile, content);
    }

    /**
     * 获取短信内容
     *
     * @param smsConfig
     * @param useChannel
     * @param params
     * @return
     */
    private String getSmsContent(SmsConfig smsConfig, SmsMessageTag useChannel, Object... params) {
        String content = "Your verification code is %s";
        SmsLocale locale = smsConfig.findLocaleByCurrentLanguage(SmsLocale.class);
        switch (useChannel) {
            case LOGIN:
                content = locale.getLoginSms();
                break;
            case REGISTER:
                content = locale.getRegisterSms();
                break;
        }
        if (!ExtendObjects.isEmpty(params)) {
            content = String.format(content, params);
        }
        return content;
    }
}
