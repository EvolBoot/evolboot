package org.evolboot.core.autoconfigure;

import org.evolboot.core.i18n.I18NConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MessageAutoConfiguration implements MessageSourceAware {

    private final DefaultConfigProperties defaultConfigProperties;

    public MessageAutoConfiguration(DefaultConfigProperties defaultConfigProperties) {
        this.defaultConfigProperties = defaultConfigProperties;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        I18NConfig.config(messageSource);
    }

}
