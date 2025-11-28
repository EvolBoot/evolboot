package org.evolboot.captcha.domain.imagecaptcha.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.entity.AbstractEntity;
import org.evolboot.core.entity.AggregateRoot;
import org.evolboot.core.entity.IdGenerate;
import org.evolboot.core.i18n.I18NMessageHolder;
import org.evolboot.core.util.Assert;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

import static org.evolboot.captcha.CaptchaI18nMessage.ImageCaptcha.CODE_ERROR;

/**
 * @author evol
 */
@Table(name = "evoltb_image_captcha")
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
    protected Date createAt;

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
        this.createAt = new Date();
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
        long timeoutTime = System.currentTimeMillis() - (this.createAt.getTime() + expires);
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
