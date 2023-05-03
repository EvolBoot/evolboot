package org.evolboot.captcha.domain.mobilecaptcha;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.evolboot.core.domain.AbstractEntity;
import org.evolboot.core.domain.AggregateRoot;
import org.evolboot.core.domain.IdGenerate;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.cache.RedisCacheName;
import org.evolboot.shared.sms.SmsMessageTag;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.Date;

/**
 * @author evol
 * 
 */
@Table(name = "evoltb_mobile_captcha")
@Slf4j
@Entity
@Getter
@NoArgsConstructor
@ToString
public class MobileCaptcha extends AbstractEntity<String> implements AggregateRoot<MobileCaptcha> {

    /**
     * 最大验证次数
     */
    @Transient
    private static final int MAX_LIMIT_VERIFY_CONT = MobileCaptchaConfiguration.getValue().getLimitVerifyCount();

    @Transient
    public final static String TOKEN_PREFIX = RedisCacheName.MOBILE_CAPTCHA_CACHE_KEY;

    @Id
    private String token;

    private String mobilePrefix;

    private String mobile;

    private String smsContent;

    private String code;

    private Integer limitVerifyCount = 0;

    private String ip;

    private Boolean verifyResult = false;

    private String internalCode;

    /**
     * 有效期
     */
    private Long expires = MobileCaptchaConfiguration.getValue().getExpires();

    /**
     * 下次获取间隔 1 分钟
     */
    private Long interval = MobileCaptchaConfiguration.getValue().getInterval();

    private SmsMessageTag messageTag;

    protected Date createAt;

    private void generateId() {
        this.token = IdGenerate.stringId();
    }

    private void setMobile(String mobile) {
        this.mobile = mobile;
    }

    private void setCode(String code) {
        this.code = code;
    }


    private void setMessageTag(SmsMessageTag messageTag) {
        this.messageTag = messageTag;
    }

    private void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    private void setIp(String ip) {
        this.ip = ip;
    }

    public void setMobilePrefix(String mobilePrefix) {
        this.mobilePrefix = mobilePrefix;
    }

    public void setInternalCode(String internalCode) {
        this.internalCode = internalCode;
    }

    @Builder
    public MobileCaptcha(String mobilePrefix, String mobile, String code, SmsMessageTag messageTag, String smsContent, String internalCode, String ip) {
        generateId();
        setMobilePrefix(mobilePrefix);
        setMobile(mobile);
        setCode(code);
        setMessageTag(messageTag);
        setSmsContent(smsContent);
        setIp(ip);
        setInternalCode(internalCode);
        this.createAt = new Date();
    }

    public boolean verify(String mobilePrefix, String mobile, String code, String internalCode) {
        if (this.verifyResult) {
            return false;
        }
        if (isTimeout()) {
            return false;
        }
        limitVerifyCount++;
        this.verifyResult = this.mobilePrefix.equalsIgnoreCase(mobilePrefix) && this.mobile.equals(mobile) && this.code.equals(code);
        if (ExtendObjects.isNotBlank(internalCode)) {
            this.verifyResult = this.verifyResult && internalCode.equals(this.internalCode);
        }
        return verifyResult;
    }

    @JsonIgnore
    public Long getRemainExpires() {
        return expires - (System.currentTimeMillis() - (createAt.getTime()));
    }

    @JsonIgnore
    public boolean isTimeout() {
        if (limitVerifyCount >= MAX_LIMIT_VERIFY_CONT) {
            return true;
        }
        long lastExpires = getRemainExpires();
        return lastExpires <= 0;
    }

    @JsonIgnore
    public boolean isAllowGetNext() {
        long timeoutTime = interval - (System.currentTimeMillis() - createAt.getTime());
        return timeoutTime <= 0;
    }

    @Override
    public String id() {
        return token;
    }

    @Override
    public MobileCaptcha root() {
        return this;
    }

}
