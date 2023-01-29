package org.evolboot.core.util;

import cn.hutool.extra.mail.MailUtil;

/**
 * @author evol
 * 
 */
public class EmailSenderUtil {

    public String sendText(String to, String subject, String content) {
        return MailUtil.send(to, subject, content, false);
    }

}
