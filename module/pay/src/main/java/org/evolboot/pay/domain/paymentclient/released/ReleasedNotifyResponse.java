package org.evolboot.pay.domain.paymentclient.released;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderNotifyResult;
import org.evolboot.shared.pay.PayoutOrderState;

/** 代付通知 回复 */
@Getter
@AllArgsConstructor
public class ReleasedNotifyResponse {
    private PayoutOrderState state;
    private String returnText;
    private PayoutOrderNotifyResult notifyResult;
}
