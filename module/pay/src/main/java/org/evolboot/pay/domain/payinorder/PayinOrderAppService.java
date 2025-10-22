package org.evolboot.pay.domain.payinorder;

import org.evolboot.pay.domain.paymentclient.payin.PayinNotifyRequest;
import org.evolboot.pay.domain.paymentclient.payin.PayinRedirectNotifyRequest;
import org.evolboot.pay.domain.payinorder.entity.PayinOrder;
import org.evolboot.pay.domain.payinorder.service.PayinOrderCreateFactory;

/**
 * 第三方代收订单
 *
 * @author evol
 */
public interface PayinOrderAppService {

    /**
     * 创建订单
     *
     * @param request
     * @return
     */
    PayinOrder create(PayinOrderCreateFactory.Request request);


    <T extends PayinNotifyRequest> Object payinOrderNotify(T request);

    <T extends PayinRedirectNotifyRequest> String getPayinRedirectUrl(T request);


}
