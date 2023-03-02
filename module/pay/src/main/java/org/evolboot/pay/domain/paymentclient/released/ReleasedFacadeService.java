package org.evolboot.pay.domain.paymentclient.released;

/**
 * @author evol
 */
public interface ReleasedFacadeService {


    /**
     * 下发(代付)订单
     *
     * @param request
     * @return
     */
    ReleasedCreateResponse createReleasedOrder(ReleasedCreateRequest request);


    /**
     * 下发(代付)通知
     *
     * @param request
     * @param <T>
     * @return
     */
    <T extends ReleasedNotifyRequest> Object releasedOrderNotify(T request);

}
