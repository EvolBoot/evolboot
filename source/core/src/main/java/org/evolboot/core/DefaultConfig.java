package org.evolboot.core;

import org.evolboot.core.util.ExtendObjects;
import lombok.Builder;
import lombok.Getter;

import java.util.Locale;

/**
 * @author evol
 */

@Getter
public class DefaultConfig {

    private String timeZone = "GMT+8";
    private Locale defaultLanguage = Locale.US;
    private String defaultLanguageTag = defaultLanguage.toLanguageTag();
    private Boolean testModel = false;
    private String testKey = "";

    private static DefaultConfig instance = new DefaultConfig();

    private static boolean allowUpdate = true;

    private DefaultConfig() {

    }

    @Builder
    public DefaultConfig(String timeZone, Locale defaultLanguage, Boolean testModel, String testKey) {
        this.timeZone = timeZone;
        this.defaultLanguage = defaultLanguage;
        this.defaultLanguageTag = defaultLanguage.toLanguageTag();
        this.testModel = testModel;
        this.testKey = testKey;
    }

    public static void setDefaultConfig(DefaultConfig defaultConfig) {
        if (allowUpdate && ExtendObjects.nonNull(defaultConfig)) {
            DefaultConfig.instance = defaultConfig;
            DefaultConfig.allowUpdate = false;
        }
    }


    public static String getTimeZone() {
        return instance.timeZone;
    }

    public static Locale getDefaultLanguage() {
        return instance.defaultLanguage;
    }

    public static String getDefaultLanguageTag() {
        return instance.defaultLanguageTag;
    }

    public static Boolean getTestModel() {
        return instance.testModel;
    }

    public static String getTestKey() {
        return instance.testKey;
    }
}
