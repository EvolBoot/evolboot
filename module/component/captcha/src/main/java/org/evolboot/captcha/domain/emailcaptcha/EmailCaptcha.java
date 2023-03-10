package org.evolboot.captcha.domain.emailcaptcha;

import cn.hutool.core.date.DateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.email.MessageTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

/**
 * @author evol
 */
@Table(name = "evoltb_email_captcha")
@Slf4j
@Entity
@Getter
@NoArgsConstructor
@ToString
public class EmailCaptcha extends AbstractEntity<String> implements AggregateRoot<EmailCaptcha> {


    /**
     * 最大验证次数
     */
    @Transient
    private static final int MAX_LIMIT_VERIFY_CONT = EmailCaptchaConfiguration.getValue().getLimitVerifyCount();

    @Id
    private String token;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 验证
     */
    private String code;

    private Integer limitVerifyCount = 0;

    private String ip;

    private Boolean verifyResult = false;

    private String internalCode;

    /**
     * 有效期
     */
    private Long expires = EmailCaptchaConfiguration.getValue().getExpires();

    /**
     * 下次获取间隔
     */
    private Long interval = EmailCaptchaConfiguration.getValue().getInterval();

    @Enumerated(EnumType.STRING)
    private MessageTag messageTag;

    @Temporal(TemporalType.TIMESTAMP)
    @org.hibernate.annotations.CreationTimestamp
    protected Date createTime;

    private void generateId() {
        this.token = IdGenerate.stringId();
    }

    private void setEmail(String email) {
        this.email = email;
    }

    private void setCode(String code) {
        this.code = code;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    private void setMessageTag(MessageTag messageTag) {
        this.messageTag = messageTag;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    @Builder
    public EmailCaptcha(String email, String code, MessageTag messageTag, String internalCode, String ip) {
        this.createTime = new DateTime();
        generateId();
        setEmail(email);
        setCode(code);
        setIp(ip);
        setMessageTag(messageTag);
        setInternalCode(internalCode);
    }

    public boolean verify(String email, String code, String internalCode) {
        if (this.verifyResult) {
            return false;
        }
        if (isTimeout()) {
            return false;
        }
        limitVerifyCount++;
        this.verifyResult = this.email.equals(email) && this.code.equals(code);
        if (ExtendObjects.isNotBlank(internalCode)) {
            this.verifyResult = this.verifyResult && internalCode.equals(this.internalCode);
        }
        return this.verifyResult;
    }

    @JsonIgnore
    public Long getRemainExpires() {
        return expires - (System.currentTimeMillis() - (createTime.getTime()));
    }

    @JsonIgnore
    public boolean isAllowGetNext() {
        long timeoutTime = interval - (System.currentTimeMillis() - createTime.getTime());
        return timeoutTime <= 0;
    }

    @JsonIgnore
    public boolean isTimeout() {
        if (limitVerifyCount >= MAX_LIMIT_VERIFY_CONT) {
            return true;
        }
        long timeoutTime = getRemainExpires();
        return timeoutTime <= 0;
    }

    @Override
    public String id() {
        return token;
    }

    @Override
    public EmailCaptcha root() {
        return this;
    }

}
