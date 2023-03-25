package org.evolboot.pay.domain.receiptorder;

import org.evolboot.pay.domain.receiptorder.repository.ReceiptOrderRepository;
import org.evolboot.pay.domain.receiptorder.service.ReceiptOrderSupportService;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.pay.domain.releasedorder.service.ReleasedOrderSendService;
import org.evolboot.shared.event.pay.ReleasedOrderCreatedMessage;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方代收订单
 *
 * @author evol
 */
@Service
@Slf4j
public class ReceiptOrderListener  {




}
