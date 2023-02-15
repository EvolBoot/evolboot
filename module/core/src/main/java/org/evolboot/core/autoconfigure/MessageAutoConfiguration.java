package org.evolboot.core.autoconfigure;

import org.evolboot.core.i18n.DefaultMessageSourceAccessor;
import org.evolboot.core.i18n.I18NConfig;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.i18n.spring.SpringMessageHolder;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
@Slf4j
public class MessageAutoConfiguration implements MessageSourceAware {

    private final DefaultConfigProperties defaultConfigProperties;

    public MessageAutoConfiguration(DefaultConfigProperties defaultConfigProperties) {
        this.defaultConfigProperties = defaultConfigProperties;
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        log.info("I18N国际化配置为本地Spring配置文件,默认语言是:{}", defaultConfigProperties.getLanguage());
        SpringMessageHolder springMessageHolder = new SpringMessageHolder(new DefaultMessageSourceAccessor(messageSource, Locale.forLanguageTag(defaultConfigProperties.getLanguage())));
        I18NConfig.config(springMessageHolder);
    }

}
