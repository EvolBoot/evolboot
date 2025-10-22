package org.evolboot.pay;


import org.evolboot.core.annotation.AuthorityModule;
import org.evolboot.core.annotation.AuthorityResource;

import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_PREFIX;
import static org.evolboot.security.api.access.AccessAuthorities.AUTHORITY_SUFFIX;

/**
 * 权限控制标识符
 *
 * @author evol
 */
@AuthorityModule(value = "pay", label = "支付管理")
public interface PayAccessAuthorities {


    /**
     * 第三方代收订单
     */
    @AuthorityResource(value = "payinorder", label = "代收订单")
    interface PayinOrder {
        String HAS_CREATE = AUTHORITY_PREFIX + "pay_payinorder_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "pay_payinorder_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "pay_payinorder_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "pay_payinorder_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "pay_payinorder_single" + AUTHORITY_SUFFIX;
    }

    /**
     * 支付网关账户
     */
    @AuthorityResource(value = "paygatewayaccount", label = "支付网关账户")
    interface PayGatewayAccount {
        String HAS_CREATE = AUTHORITY_PREFIX + "pay_receiptorder_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "pay_receiptorder_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "pay_receiptorder_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "pay_receiptorder_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "pay_receiptorder_single" + AUTHORITY_SUFFIX;
    }


    /**
     * 代付订单
     */
    @AuthorityResource(value = "releasedorder", label = "代付订单")
    interface ReleasedOrder {
        String HAS_CREATE = AUTHORITY_PREFIX + "pay_releasedorder_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "pay_releasedorder_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "pay_releasedorder_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "pay_releasedorder_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "pay_releasedorder_single" + AUTHORITY_SUFFIX;
    }
    /**
     * 测试产品
     */
    @AuthorityResource(value = "testproduct", label = "测试产品")
    interface TestProduct {
        String HAS_CREATE = AUTHORITY_PREFIX + "pay_testproduct_create" + AUTHORITY_SUFFIX;
        String HAS_DELETE = AUTHORITY_PREFIX + "pay_testproduct_delete" + AUTHORITY_SUFFIX;
        String HAS_UPDATE = AUTHORITY_PREFIX + "pay_testproduct_update" + AUTHORITY_SUFFIX;
        String HAS_PAGE = AUTHORITY_PREFIX + "pay_testproduct_page" + AUTHORITY_SUFFIX;
        String HAS_SINGLE = AUTHORITY_PREFIX + "pay_testproduct_single" + AUTHORITY_SUFFIX;
    }

}
