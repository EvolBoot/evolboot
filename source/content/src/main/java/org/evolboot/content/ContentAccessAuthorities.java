package org.evolboot.content;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * @author evol
 * 
 */
public interface ContentAccessAuthorities {


    interface News {
        String HAS_CREATE = AUTHORITY_PREFIX + "content_news_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "content_news_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "content_news_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "content_news_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "content_news_single" + AUTHORITY_SUFFIX;
    }

    /**
     * banner
     */
    interface Banner {
        String HAS_CREATE = AUTHORITY_PREFIX + "content_banner_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "content_banner_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "content_banner_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "content_banner_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "content_banner_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 启动页
     */
    interface StartupPage {
        String HAS_CREATE = AUTHORITY_PREFIX + "content_startuppage_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "content_startuppage_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "content_startuppage_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "content_startuppage_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "content_startuppage_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 公告
     */
    interface Notice {
        String HAS_CREATE = AUTHORITY_PREFIX + "content_notice_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "content_notice_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "content_notice_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "content_notice_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "content_notice_single" + AUTHORITY_SUFFIX;
    }


    /**
     * QA
     */
    interface Qa {
        String HAS_CREATE = AUTHORITY_PREFIX + "content_qa_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "content_qa_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "content_qa_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "content_qa_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "content_qa_single" + AUTHORITY_SUFFIX;
    }


}
