package org.evolboot.system;


import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 系统模块
 *
 * @author evol
 */
public interface SystemAccessAuthorities {


    interface UserLoginLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_userloginlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_userloginlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_userloginlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_userloginlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_userloginlog_single" + AUTHORITY_SUFFIX;
    }

    interface OperationLog {
        String HAS_CREATE = AUTHORITY_PREFIX + "identity_operationlog_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "identity_operationlog_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "identity_operationlog_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "identity_operationlog_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "identity_operationlog_single" + AUTHORITY_SUFFIX;
    }


    /**
     * APP更新
     */
    interface AppUpgrade {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_appupgrade_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_appupgrade_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_appupgrade_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_appupgrade_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_appupgrade_single" + AUTHORITY_SUFFIX;
    }



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


    /**
     * 字典key
     */
    interface DictKey {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_dictkey_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_dictkey_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_dictkey_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_dictkey_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_dictkey_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 字典Value
     */
    interface DictValue {
        String HAS_CREATE = AUTHORITY_PREFIX + "system_dictvalue_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "system_dictvalue_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "system_dictvalue_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "system_dictvalue_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "system_dictvalue_single" + AUTHORITY_SUFFIX;
    }





}
