package org.evolboot.security.accesstoken.acl.client;

/**
 * @author evol
 *
 */
public interface CaptchaClient {

    void verifyMobileCaptchaIsTrue(String mobilePrefix, String mobile, String mobileCaptchaCode, String mobileCaptchaToken);

}
