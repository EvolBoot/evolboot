package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import org.evolboot.core.util.Assert;
import org.evolboot.core.util.BigDecimalUtil;
import org.evolboot.core.util.ExtendHttpUtil;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.receipt.*;
import org.evolboot.pay.domain.paymentclient.released.*;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderNotifyResult;
import org.evolboot.pay.domain.receiptorder.ReceiptOrderRequestResult;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderNotifyResult;
import org.evolboot.pay.domain.releasedorder.ReleasedOrderRequestResult;
import org.evolboot.pay.exception.PayException;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

/**
 * @author evol
 */
@Slf4j
@Service
public class HuanQiuPayPaymentClient implements ReceiptClient, ReleasedClient {

    private final HuanQiuPayConfig huanQiuPayConfig;

    private static final int POINT = 2;

    public HuanQiuPayPaymentClient(HuanQiuPayConfig huanQiuPayConfig) {
        this.huanQiuPayConfig = huanQiuPayConfig;
    }


    @Override
    public PayGateway getPayGateway() {
        return PayGateway.HUANQIU_PAY;
    }

    /**
     * 发起代收
     *
     * @param receiptOrderId
     * @param account
     * @param request
     * @return
     */
    @Override
    public ReceiptOrderResponse createReceiptOrder(String receiptOrderId, PayGatewayAccount account, ReceiptOrderRequest request) {
        log.info("代收:支付:创建订单:开始:HuanQiuPay");
        Assert.isTrue(BigDecimalUtil.goe(request.getPayAmount(), account.getMinimumReceipt()), PayI18nMessage.PaymentClient.theMinimumRechargeAmountIs(account.getMinimumReceipt()));
        String url = huanQiuPayConfig.getReceiptUrl();
        TreeMap<String, Object> params = Maps.newTreeMap();
        params.put("mer_id", account.getMerchantId());
        params.put("timestamp", HuanQiuPayUtil.getDate());
        params.put("terminal", "H5");
        params.put("version", "01");
        params.put("amount", request.getPayAmount().movePointRight(POINT));
        params.put("backurl", huanQiuPayConfig.getSuccessUrl());
        params.put("failUrl", huanQiuPayConfig.getFailUrl());
        params.put("ServerUrl", huanQiuPayConfig.getReceiptNotifyUrl());
        params.put("businessnumber", receiptOrderId);
        params.put("goodsName", "GoodsName");

        // 签名
        params.put("sign", HuanQiuPayUtil.sign(params, account.getSecretKey()));

        params.put("sign_type", "md5");

        String postData = ExtendHttpUtil.post(url, params);

        log.info("代收请求参数:{},返回结果:{}", JsonUtil.stringify(params), postData);

        ReceiptRequestResult result = JsonUtil.parse(postData, HuanQiuPayReceiptRequestResult.class);

        if (result.isOk()) {
            String payUrl = result.getPayUrl();
            return new ReceiptOrderResponse(result.isOk(), receiptOrderId, payUrl, new ReceiptOrderRequestResult(result.getForeignOrderId(), payUrl, postData));
        }
        log.info("代收失败,返回信息:{},{}", result.getStatus(), postData);
        throw new PayException("Pay Fail");

    }


    /**
     * 代收通知
     *
     * @param gatewayAccount
     * @param request
     * @param <T>
     * @return
     */
    @Override
    public <T extends ReceiptNotifyRequest> ReceiptNotifyResponse receiptOrderNotify(PayGatewayAccount gatewayAccount, T request) {
        String requestParamsText = request.getNotifyParamsText();
        log.info("HuanQiu:代收:回调通知:{}", requestParamsText);
        return new ReceiptNotifyResponse(request.isOk(),
                "success",
                new ReceiptOrderNotifyResult(
                        request.getForeignOrderId(),
                        request.getReceiptOrderId(),
                        request.getStatus(),
                        requestParamsText,
                        request.getPayAmount(),
                        request.getRealPayAmount(),
                        request.getPoundage()
                ));
    }


    @Override
    public ReleasedOrderResponse createReleasedOrder(String releasedOrderId, PayGatewayAccount account, ReleasedOrderRequest request) {
        log.info("代付:创建订单");
        String url = huanQiuPayConfig.getReleasedUrl();
        String backUrl = huanQiuPayConfig.getReleasedNotifyUrl();
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("mer_id", account.getMerchantId());
        map.put("timestamp", HuanQiuPayUtil.getDate());
        map.put("terminal", "H5");
        map.put("version", "01");
        map.put("amount", request.getAmount().movePointRight(2));
        map.put("businessnumber", releasedOrderId);
        map.put("bankcardnumber", request.getBankNo());
        map.put("bankcardname", request.getPayeeName());
        map.put("bankname", request.getBankCode());
        map.put("back_url", backUrl);
        map.put("cash_type", "3");
        map.put("wallet_id", account.getWalletId());
        // 签名
        map.put("sign", HuanQiuPayUtil.sign(map, account.getSecretKey()));
        map.put("sign_type", "md5");

        String post = ExtendHttpUtil.post(url, map);
        String requestText = JsonUtil.stringify(map);
        log.info("代付:发起请求:{},参数:{},返回:{}", releasedOrderId, JsonUtil.stringify(map), post);
        ReleasedRequestResult result = JsonUtil.parse(post, HuanQiuPayReleasedRequestResult.class);
        return new ReleasedOrderResponse(
                result.isOk(),
                request.getAmount(),
                BigDecimal.ZERO,
                result.getForeignOrderId(),
                releasedOrderId,
                new ReleasedOrderRequestResult(
                        requestText, post, result.getForeignOrderId(), result.getStatus()
                )
        );
    }

    @Override
    public <T extends ReleasedNotifyRequest> ReleasedNotifyResponse releasedOrderNotify(PayGatewayAccount gatewayAccount, T request) {
        return new ReleasedNotifyResponse(request.isOk(),
                "success",
                new ReleasedOrderNotifyResult(
                        request.getForeignOrderId(),
                        request.getReleasedOrderId(),
                        request.getStatus(),
                        request.getNotifyParamsText(),
                        request.getAmount(),
                        request.getPoundage()
                ));
    }


    /**
     * 代收
     *
     * @param args
     */
    public static void main2(String[] args) {
        proxy();
        String url = "http://api.xiongwei2000.com/customer.pay";
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("mer_id", "xt171219211449");
        map.put("timestamp", HuanQiuPayUtil.getDate());
        map.put("terminal", "H5");
        map.put("version", "01");
        map.put("amount", "20000000");
        map.put("backurl", "http://www.sdbtob.cn/");
        map.put("failUrl", "http://www.sdbtob.cn/sell/");
        map.put("ServerUrl", "https://api-test.404root.com/v1/api/pay/payment/huan-qiu-pay/notify-test");
        map.put("businessnumber", System.currentTimeMillis() + "");
        map.put("goodsName", "测试商品");

        map.put("sign", HuanQiuPayUtil.sign(map, "F6JQVHQLDZYDK0SLPIIEXNKCYFMWNTDY"));
        map.put("sign_type", "md5");

        String post = ExtendHttpUtil.post(url, map);
        HuanQiuPayReceiptRequestResult parse = JsonUtil.parse(post, HuanQiuPayReceiptRequestResult.class);
        System.out.println(parse.getData().getTrade_qrcode());
        System.out.println(post);
    }

    /**
     * 代付
     *
     * @param args
     */
    public static void main(String[] args) {
        proxy();
        log.info("test");
        BigDecimal amount = new BigDecimal("20043.12");
        BigDecimal bigDecimal = amount.movePointRight(2);
        String url = "http://api.xiongwei2000.com/pay.bank.to";
        String backUrl = "https://api-test.404root.com/v1/api/pay/payment/huan-qiu-pay/released/notify";
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("mer_id", "xt171219211449");
        map.put("timestamp", HuanQiuPayUtil.getDate());
        map.put("terminal", "H5");
        map.put("version", "01");
        map.put("amount", bigDecimal);
        map.put("businessnumber", System.currentTimeMillis() + "");
        map.put("bankcardnumber", "1213123123");
        map.put("bankcardname", "test");
        map.put("bankname", "COMMONWEALTH");
        map.put("back_url", backUrl);
        map.put("cash_type", "3");
        map.put("wallet_id", "49");

        map.put("sign", HuanQiuPayUtil.sign(map, "F6JQVHQLDZYDK0SLPIIEXNKCYFMWNTDY"));
        map.put("sign_type", "md5");


        System.out.println(JsonUtil.stringify(map));
        String post = ExtendHttpUtil.post(url, map);
        System.out.println(post);
        HuanQiuPayReleasedRequestResult parse = JsonUtil.parse(post, HuanQiuPayReleasedRequestResult.class);
        System.out.println(parse.isOk());
        System.out.println(new BigDecimal(parse.getData().getAmount()).movePointLeft(2));

//        String s = ExtendHttpUtil.get("http://www.cip.cc/");
//        System.out.println(s);
    }


    private static void proxy() {
        System.setProperty("http.proxyHost", "127.0.0.1");
        System.setProperty("http.proxyPort", "1087");
        System.setProperty("https.proxyHost", "127.0.0.1");
        System.setProperty("https.proxyPort", "1087");
        System.setProperty("socksProxyHost", "127.0.0.1");
        System.setProperty("socksProxyPort", "1080");

    }

}
