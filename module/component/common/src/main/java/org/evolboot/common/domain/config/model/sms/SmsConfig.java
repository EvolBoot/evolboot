package org.evolboot.common.domain.config.model.sms;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.evolboot.common.domain.config.serivce.PropertyValue;
import org.evolboot.core.entity.LocaleDomainPart;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.sms.SmsSendChannel;

import java.util.List;

/**
 * @author evol
 */
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
public class SmsConfig implements PropertyValue, LocaleDomainPart<SmsLocale> {

    public final static String KEY = "sms";

    private static final List<SmsLocale> DEFAULT = List.of(new SmsLocale());

    private SmsSendChannel channel;

    private List<SmsLocale> locales;

    @Override
    public void check() {

    }


    public List<SmsLocale> getLocales() {
        return ExtendObjects.requireNonNullElse(locales, DEFAULT);
    }

    public SmsSendChannel getChannel() {
        return ExtendObjects.requireNonNullElse(channel, SmsSendChannel.YUN_PIAN);
    }

    @Override
    public String key() {
        return KEY;
    }
}
