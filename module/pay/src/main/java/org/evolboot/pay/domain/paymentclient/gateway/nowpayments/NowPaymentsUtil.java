package org.evolboot.pay.domain.paymentclient.gateway.nowpayments;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.ExtendObjects;
import org.evolboot.shared.pay.Currency;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

/**
 * NOWPayments 工具类
 *
 * @author evol
 */
@Slf4j
@UtilityClass
public class NowPaymentsUtil {

    /**
     * 虚拟币映射
     */
    private static final Map<Currency, String> CURRENCY_MAPPING = new HashMap<>();

    static {
        CURRENCY_MAPPING.put(Currency.ETH, "eth");
        CURRENCY_MAPPING.put(Currency.BTC, "btc");
        CURRENCY_MAPPING.put(Currency.USDT, "usdterc20");
        CURRENCY_MAPPING.put(Currency.USDC, "usdcerc20");
    }

    /**
     * 转换系统币种到 NOWPayments 币种代码
     */
    public static String convertCurrency(Currency currency) {
        String payCurrency = CURRENCY_MAPPING.get(currency);
        if (ExtendObjects.isNull(payCurrency)) {
            throw new IllegalArgumentException("不支持的币种: " + currency);
        }
        return payCurrency;
    }

    /**
     * 验证 IPN 回调签名
     *
     * @param payload   回调内容
     * @param signature 签名 (x-nowpayments-sig header)
     * @param ipnSecret IPN Secret
     * @return 是否验证通过
     */
    public static boolean verifyIpnSignature(String payload, String signature, String ipnSecret) {
        try {
            Mac hmacSha512 = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(
                ipnSecret.getBytes(StandardCharsets.UTF_8),
                "HmacSHA512"
            );
            hmacSha512.init(secretKeySpec);
            byte[] hash = hmacSha512.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            String calculatedSignature = bytesToHex(hash);

            boolean isValid = calculatedSignature.equalsIgnoreCase(signature);
            if (!isValid) {
                log.warn("NOWPayments IPN 签名验证失败. Expected: {}, Got: {}", calculatedSignature, signature);
            }
            return isValid;
        } catch (Exception e) {
            log.error("NOWPayments IPN 签名验证异常", e);
            return false;
        }
    }

    /**
     * 字节数组转十六进制字符串
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }

    /**
     * 获取法币代码
     * NOWPayments 推荐使用 USD 作为定价货币
     */
    public static String getPriceCurrency() {
        return "usd";
    }
}
