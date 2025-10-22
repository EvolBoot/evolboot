package org.evolboot.shared.cache;

/**
 * @author evol
 */
public interface RedisCacheName {


    /**
     * 配置中心
     */
    String CONFIGURATION_CACHE_NAME = "evoltb_configuration_";


    /**
     * 用户会话
     */
    String ACCESS_TOKEN_PREFIX = "evoltb_session";


    /**
     * 用户ID
     */
    String IDENTITY_USER_ID_PREFIX = "evoltb_user_id_";


    /**
     * 本地化(城市)
     */
    String LOCALE_COUNTRY_CACHE_KEY = "evoltb_locale_country_";

    /**
     * 本地化（语言)
     */
    String LOCALE_LANGUAGE_CACHE_KEY = "evoltb_locale_language_";

    /**
     * 邮箱验证码
     */
    String EMAIL_CAPTCHA_CACHE_KEY = "evoltb_email";

    /**
     * 图形验证码
     */
    String IMAGE_CAPTCHA_CACHE_KEY = "evoltb_img";

    /**
     * 手机验证码
     */
    String MOBILE_CAPTCHA_CACHE_KEY = "evoltb_mobile";

    /**
     * Banner
     */
    String BANNER_CACHE_KEY = "evoltb_banner_";

    /**
     * 新闻
     */
    String NEWS_CACHE_KEY = "evoltb_news_";

    /**
     * Notice
     */
    String NOTICE_CACHE_KEY = "evoltb_notice_";

    /**
     * 问答
     */
    String QA_CACHE_KEY = "evoltb_qa_";

    /**
     * 启动页
     */
    String STARTUP_PAGE_CACHE_KEY = "evoltb_startup_page_";

    /**
     * APP更新
     */
    String APP_UPGRADE_CACHE_KEY = "evoltb_app_upgrade_";


    /**
     * 代收跳转数据暂存
     */
    String PAY_PAYIN_REDIRECT_URL_CACHE_KEY = "evoltb_payin_redirect_url_";
    /**
     * 代收跳转数据暂存
     */
    String DICT_CACHE_KEY = "evoltb_dict_";
}
