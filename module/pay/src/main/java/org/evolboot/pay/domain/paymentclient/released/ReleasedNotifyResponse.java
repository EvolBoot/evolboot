package org.evolboot.pay.domain.paymentclient.released;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.releasedorder.entity.ReleasedOrderNotifyResult;
import org.evolboot.shared.pay.ReleasedOrderState;

/**
 * 代付通知 回复
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public class ReleasedNotifyResponse {

    private ReleasedOrderState state;

    private String returnText;

    private ReleasedOrderNotifyResult notifyResult;

}
