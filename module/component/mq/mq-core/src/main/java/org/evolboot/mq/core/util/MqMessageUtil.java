package org.evolboot.mq.core.util;

/**
 * @author evol
 */
public final class MqMessageUtil {

    public static Class<?> getMessageClass(String messageClazz) {
        try {
            return Class.forName(messageClazz);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
