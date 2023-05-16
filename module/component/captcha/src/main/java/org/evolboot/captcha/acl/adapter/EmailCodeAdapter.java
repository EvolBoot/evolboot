package org.evolboot.captcha.acl.adapter;

import lombok.extern.slf4j.Slf4j;
import org.evolboot.captcha.acl.client.EmailCodeClient;
import org.evolboot.core.util.EmailSenderUtil;
import org.springframework.stereotype.Component;

/**
 * @author evol
 */
@Component
@Slf4j
public class EmailCodeAdapter implements EmailCodeClient {

    private final EmailSenderUtil emailSender = new EmailSenderUtil();

    @Override
    public Response send(String email, String code) {
        String res = emailSender.sendText(email, "你的验证码", "您的验证为:" + code);
        log.info("邮件验证码，回执:{}", res);
        return EmailCodeClient.Response.builder()
                .sendStatus(true)
                .build();
    }

}
