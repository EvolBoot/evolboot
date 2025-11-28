package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.BigDecimalUtil;
import org.evolboot.core.util.ExtendHttpUtil;
import org.evolboot.core.util.JsonUtil;
import org.evolboot.pay.PayI18nMessage;
import org.evolboot.pay.domain.paygatewayaccount.entity.PayGatewayAccount;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.payin.HuanQiuPayForeignPayinCreateResponse;
import org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.payout.HuanQiuPayForeignPayoutCreateResponse;
import org.evolboot.pay.domain.paymentclient.payin.*;
import org.evolboot.pay.domain.paymentclient.payout.*;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderNotifyResult;
import org.evolboot.pay.domain.payinorder.entity.PayinOrderRequestResult;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderCreateResult;
import org.evolboot.pay.domain.payoutorder.entity.PayoutOrderNotifyResult;
import org.evolboot.pay.exception.PayException;
import org.evolboot.shared.pay.Currency;
import org.evolboot.shared.pay.PayGateway;
import org.evolboot.shared.pay.PayoutOrderOrgType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.TreeMap;

import static org.evolboot.pay.exception.PayException.PAYOUT_ORDER_ERROR;

/**
 * @author evol
 */
@Slf4j
@Service("HuanQiuPayPaymentClient")
public class HuanQiuPayPaymentClient implements PayinClient, PayoutClient {

    private final HuanQiuPayConfig huanQiuPayConfig;

    private static final int POINT = 2;

    public HuanQiuPayPaymentClient(HuanQiuPayConfig huanQiuPayConfig) {
        this.huanQiuPayConfig = huanQiuPayConfig;
    }


    @Override
    public PayGateway getPayGateway() {
        return PayGateway.HUANQIU_PAY;
    }

    @Override
    public boolean supportCurrency(Currency currency) {
        return true;
    }

    /**
     * 发起代收
     *
     * @param payinOrderId
     * @param account
     * @param request
     * @return
     */
    @Override
    public PayinCreateResponse createPayinOrder(String payinOrderId, PayGatewayAccount account, PayinCreateRequest request) {
        log.info("代收:支付:创建订单:开始:HuanQiuPay");
        String url = huanQiuPayConfig.getPayinCreateUrl();
        TreeMap<String, Object> params = Maps.newTreeMap();
        params.put("mer_id", account.getMerchantId());
        params.put("timestamp", HuanQiuPayUtil.getDate());
        params.put("terminal", "H5");
        params.put("version", "01");
        params.put("amount", request.getPayAmount().movePointRight(POINT));
        params.put("backurl", huanQiuPayConfig.getSuccessUrl());
        params.put("failUrl", huanQiuPayConfig.getFailUrl());
        params.put("ServerUrl", huanQiuPayConfig.getPayinCreateNotifyUrl());
        params.put("businessnumber", payinOrderId);
        params.put("goodsName", "GoodsName");

        // 签名
        params.put("sign", HuanQiuPayUtil.sign(params, account.getSecretKey()));

        params.put("sign_type", "md5");

        String postData = ExtendHttpUtil.post(url, params);

        log.info("代收请求参数:{},返回结果:{}", JsonUtil.stringify(params), postData);

        HuanQiuPayForeignPayinCreateResponse response = JsonUtil.parse(postData, HuanQiuPayForeignPayinCreateResponse.class);

        if (response.isOk()) {
            String payUrl = response.getPayUrl();
            return new PayinCreateResponse(response.isOk(), payinOrderId, payUrl, new PayinOrderRequestResult(
                response.getForeignOrderId(),
                payUrl,
                response.getState(),
                postData));
        }
        log.info("代收失败,返回信息:{},{}", response.getState(), postData);
        throw PayException.PAYIN_ORDER_ERROR;

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
    public <T extends PayinNotifyRequest> PayinNotifyResponse payinOrderNotify(PayGatewayAccount gatewayAccount, T request) {
        String requestParamsText = request.getNotifyParamsText();
        log.info("HuanQiu:代收:回调通知:{}", requestParamsText);
        return new PayinNotifyResponse(request.getState(),
                "success",
                new PayinOrderNotifyResult(
                        request.getForeignOrderId(),
                        request.getPayinOrderId(),
                        request.getForeignState(),
                        requestParamsText,
                        request.getPayAmount(),
                        request.getRealPayAmount(),
                        request.getPoundage()
                ));
    }

    @Override
    public <T extends PayinRedirectNotifyRequest> PayinRedirectNotifyResponse payinOrderRedirectNotify(PayGatewayAccount gatewayAccount, T request) {
        //TODO 验证
        return new PayinRedirectNotifyResponse(request.getState());
    }


    @Override
    public PayoutCreateResponse createPayoutOrder(String payoutOrderId, PayGatewayAccount account, PayoutCreateRequest request) {
        log.info("代付:创建订单");
        String url = huanQiuPayConfig.getPayoutCreateUrl();
        String backUrl = huanQiuPayConfig.getPayoutCreateNotifyUrl();
        TreeMap<String, Object> map = Maps.newTreeMap();
        map.put("mer_id", account.getMerchantId());
        map.put("timestamp", HuanQiuPayUtil.getDate());
        map.put("terminal", "H5");
        map.put("version", "01");
        map.put("amount", request.getAmount().movePointRight(2));
        map.put("businessnumber", payoutOrderId);
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
        log.info("代付:发起请求:{},参数:{},返回:{}", payoutOrderId, JsonUtil.stringify(map), post);
        HuanQiuPayForeignPayoutCreateResponse result = JsonUtil.parse(post, HuanQiuPayForeignPayoutCreateResponse.class);
        if (result.isOk()) {
            return new PayoutCreateResponse(
                    result.isOk(),
                    request.getAmount(),
                    BigDecimal.ZERO,
                    result.getForeignOrderId(),
                    payoutOrderId,
                    new PayoutOrderCreateResult(
                            requestText, post, result.getForeignOrderId(), result.getState()
                    )
            );
        }
        throw PAYOUT_ORDER_ERROR;

    }

    @Override
    public <T extends PayoutNotifyRequest> PayoutNotifyResponse payoutOrderNotify(PayGatewayAccount gatewayAccount, T request) {
        return new PayoutNotifyResponse(request.getState(),
                "success",
                new PayoutOrderNotifyResult(
                        request.getForeignOrderId(),
                        request.getPayoutOrderId(),
                        request.getForeignState(),
                        request.getNotifyParamsText(),
                        request.getAmount(),
                        request.getPoundage()
                ));
    }

    @Override
    public PayoutQueryResponse queryPayoutdOrder(String payoutOrderId, PayGatewayAccount account) {
        return new PayoutQueryResponse(false);
    }

    @Override
    public boolean supportOrgType(PayoutOrderOrgType orgType) {
        return PayoutOrderOrgType.IMPS.equals(orgType);
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
        HuanQiuPayForeignPayinCreateResponse parse = JsonUtil.parse(post, HuanQiuPayForeignPayinCreateResponse.class);
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
        String backUrl = "https://api-test.404root.com/v1/api/pay/payment/huan-qiu-pay/payout/notify";
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
        HuanQiuPayForeignPayoutCreateResponse parse = JsonUtil.parse(post, HuanQiuPayForeignPayoutCreateResponse.class);
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
