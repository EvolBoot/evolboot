package org.evolboot.captcha.domain.imagecaptcha;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.captcha.CaptchaI18nMessage;
import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

import static org.evolboot.captcha.CaptchaI18nMessage.ImageCaptcha.*;

/**
 * @author evol
 * 
 */
@Table(name = "evol_image_captcha")
@Entity
@Slf4j
@Getter
@NoArgsConstructor
@ToString
public class ImageCaptcha extends AbstractEntity<String> implements AggregateRoot<ImageCaptcha> {


    @Id
    private String token;

    private String code;

    private Long expires;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date createTime;

    private String ip;


    private void generateToken() {
        this.token = IdGenerate.stringId();
    }

    @Builder
    public ImageCaptcha(String code, String ip, Long expires) {
        generateToken();
        this.code = code;
        this.expires = expires;
        this.ip = ip;
        this.createTime = new Date();
    }

    public boolean verify(String code) {
        Assert.notBlank(code, I18NMessageHolder.message(CODE_ERROR));
        if (isTimeout()) {
            return false;
        }
        return this.code.equalsIgnoreCase(code);
    }

    @JsonIgnore
    public boolean isTimeout() {
        long timeoutTime = System.currentTimeMillis() - (this.createTime.getTime() + expires);
        return timeoutTime >= 0;
    }


    @Override
    public ImageCaptcha root() {
        return this;
    }

    @Override
    public String id() {
        return token;
    }
}
