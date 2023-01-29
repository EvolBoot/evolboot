package org.evolboot.shared.cache;

/**
 * @author evol
 * 
 */
public interface RedisCacheName {


    /**
     * 配置中心
     */
    String CONFIGURATION_CACHE_NAME = "evol_configuration_";


    /**
     * 用户会话
     */
    String ACCESS_TOKEN_PREFIX = "evol_session";


    /**
     * 本地化(城市)
     */
    String LOCALE_COUNTRY_CACHE_KEY = "evol_locale_country_";

    /**
     * 本地化（语言)
     */
    String LOCALE_LANGUAGE_CACHE_KEY = "evol_locale_language_";

    /**
     * 邮箱验证码
     */
    String EMAIL_CAPTCHA_CACHE_KEY = "evol_email";

    /**
     * 图形验证码
     */
    String IMAGE_CAPTCHA_CACHE_KEY = "evol_img";

    /**
     * 手机验证码
     */
    String MOBILE_CAPTCHA_CACHE_KEY = "evol_mobile";

    /**
     * Banner
     */
    String BANNER_CACHE_KEY = "evol_banner_";

    /**
     * 新闻
     */
    String NEWS_CACHE_KEY = "evol_news_";

    /**
     * Notice
     */
    String NOTICE_CACHE_KEY = "evol_notice_";

    /**
     * 问答
     */
    String QA_CACHE_KEY = "evol_qa_";

    /**
     * 启动页
     */
    String STARTUP_PAGE_CACHE_KEY = "evol_startup_page_";

    /**
     * APP更新
     */
    String APP_UPGRADE_CACHE_KEY = "evol_app_upgrade_";


}
