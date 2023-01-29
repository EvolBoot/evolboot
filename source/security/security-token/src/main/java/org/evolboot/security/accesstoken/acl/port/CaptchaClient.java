package org.evolboot.security.accesstoken.acl.port;

/**
 * @author evol
 *
 */
public interface CaptchaClient {

    void verifyMobileCaptchaIsTrue(String mobilePrefix, String mobile, String mobileCaptchaCode, String mobileCaptchaToken);

}
