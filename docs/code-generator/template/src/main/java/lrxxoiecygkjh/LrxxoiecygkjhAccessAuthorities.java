package projectPackage.lrxxoiecygkjh;


import projectPackage.core.annotation.AuthorityModule;
import projectPackage.core.annotation.AuthorityResource;

import static projectPackage.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static projectPackage.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 权限控制标识符
 *
 * @author authorxRQXP
 * @date datePlaceholder
 */
@AuthorityModule(value = "lrxxoiecygkjh", label = "需修改")
public interface LrxxoiecygkjhAccessAuthorities {


    /**
     * 模板
     */
    @AuthorityResource(value = "xarvkgvvrllnc", label = "模板")
    interface Xarvkgvvrllnc {
        String HAS_CREATE = AUTHORITY_PREFIX + "lrxxoiecygkjh_xarvkgvvrllnc_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "lrxxoiecygkjh_xarvkgvvrllnc_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "lrxxoiecygkjh_xarvkgvvrllnc_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "lrxxoiecygkjh_xarvkgvvrllnc_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "lrxxoiecygkjh_xarvkgvvrllnc_single" + AUTHORITY_SUFFIX;
    }


}
