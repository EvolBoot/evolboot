package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.releasedorder.ReleasedOrderNotifyResult;
import org.evolboot.shared.pay.ReleasedOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 代付通知 回复
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public class ReleasedNotifyResponse {

    private ReleasedOrderStatus status;

    private String returnText;

    private ReleasedOrderNotifyResult notifyResult;

}
