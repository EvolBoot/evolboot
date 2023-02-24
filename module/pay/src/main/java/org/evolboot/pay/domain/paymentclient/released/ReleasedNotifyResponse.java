package org.evolboot.pay.domain.paymentclient.released;

import org.evolboot.pay.domain.releasedorder.ReleasedOrderNotifyResult;
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

    private boolean ok;

    private String returnText;

    private ReleasedOrderNotifyResult notifyResult;

}
