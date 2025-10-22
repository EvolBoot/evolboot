package org.evolboot.pay.domain.paymentclient.payout;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderNotifyResult;
import org.evolboot.shared.pay.PayoutOrderState;

/** 代付通知 回复 */
@Getter
@AllArgsConstructor
public class PayoutNotifyResponse {
    private PayoutOrderState state;
    private String returnText;
    private PayoutOrderNotifyResult notifyResult;
}
