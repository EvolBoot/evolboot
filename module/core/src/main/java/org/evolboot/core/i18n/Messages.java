package org.evolboot.core.i18n;


import org.evolboot.Version;

/**
 *
 */
public abstract class Messages {

    private final static String NAMESPACE = Version.class.getPackageName();


    /**
     * Get the message code key.
     *
     * @param codeKey the code key for short
     * @return the full code key
     * @throws IllegalArgumentException if null
     */
    public static String codeKey(String codeKey) throws IllegalArgumentException {
        if (codeKey == null || codeKey.isBlank()) {
            throw new IllegalArgumentException("Code must not be empty");
        }
        return NAMESPACE + "." + codeKey;
    }
}
