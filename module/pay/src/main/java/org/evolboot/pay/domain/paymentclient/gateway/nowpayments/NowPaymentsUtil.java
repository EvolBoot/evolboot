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
     * 虚拟币映射 (系统币种 -> NOWPayments 币种代码)
     * 完整列表: https://documenter.getpostman.com/view/7907941/S1a32n38#9998079f-dcc8-4e07-9ac7-3d52f0fd733a
     */
    private static final Map<Currency, String> CURRENCY_MAPPING = new HashMap<>();

    static {
        // 主流虚拟币
        CURRENCY_MAPPING.put(Currency.BTC, "btc");           // Bitcoin
        CURRENCY_MAPPING.put(Currency.ETH, "eth");           // Ethereum
        CURRENCY_MAPPING.put(Currency.TRX, "trx");           // Tron
        CURRENCY_MAPPING.put(Currency.BNB, "bnbbsc");        // BNB (BSC)
        CURRENCY_MAPPING.put(Currency.SOL, "sol");           // Solana
        CURRENCY_MAPPING.put(Currency.XRP, "xrp");           // Ripple
        CURRENCY_MAPPING.put(Currency.DOGE, "doge");         // Dogecoin
        CURRENCY_MAPPING.put(Currency.ADA, "ada");           // Cardano
        CURRENCY_MAPPING.put(Currency.MATIC, "maticpoly");   // Polygon (Matic Network)
        CURRENCY_MAPPING.put(Currency.LTC, "ltc");           // Litecoin

        // 稳定币 - ERC20 (Ethereum Network)
        CURRENCY_MAPPING.put(Currency.USDT_ERC20, "usdterc20");   // Tether ERC20
        CURRENCY_MAPPING.put(Currency.USDC_ERC20, "usdcerc20");   // USD Coin ERC20
        CURRENCY_MAPPING.put(Currency.DAI_ERC20, "dai");          // DAI ERC20

        // 稳定币 - TRC20 (Tron Network) ⭐ 重点支持
        CURRENCY_MAPPING.put(Currency.USDT_TRC20, "usdttrc20");   // Tether TRC20
        CURRENCY_MAPPING.put(Currency.USDC_TRC20, "usdctrc20");   // USD Coin TRC20

        // 稳定币 - BEP20 (BSC Network)
        CURRENCY_MAPPING.put(Currency.USDT_BEP20, "usdtbsc");     // Tether BEP20
        CURRENCY_MAPPING.put(Currency.USDC_BEP20, "usdcbsc");     // USD Coin BEP20
        CURRENCY_MAPPING.put(Currency.BUSD, "busdbsc");           // Binance USD
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
