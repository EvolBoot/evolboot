package org.evolboot.shared.pay;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 货币
 *
 * @author evol
 */
@Getter
@AllArgsConstructor
public enum Currency {
    // 法币
    INR(356),
    CNY(156),
    TEST(0),

    // 主流虚拟币
    BTC(1),         // Bitcoin
    ETH(60),        // Ethereum
    TRX(195),       // Tron
    BNB(714),       // BNB (Binance Coin)
    SOL(501),       // Solana
    XRP(144),       // Ripple
    DOGE(3),        // Dogecoin
    ADA(1815),      // Cardano
    MATIC(966),     // Polygon
    LTC(2),         // Litecoin

    // 稳定币 - ERC20 (Ethereum)
    USDT_ERC20(825),      // Tether (ERC20)
    USDC_ERC20(840),      // USD Coin (ERC20)
    DAI_ERC20(6000),      // DAI (ERC20)

    // 稳定币 - TRC20 (Tron)
    USDT_TRC20(826),      // Tether (TRC20)
    USDC_TRC20(841),      // USD Coin (TRC20)

    // 稳定币 - BEP20 (BSC)
    USDT_BEP20(827),      // Tether (BEP20)
    USDC_BEP20(842),      // USD Coin (BEP20)
    BUSD(6001);           // Binance USD

    private final Integer value;

    private static final Map<Integer, Currency> VALUES = new HashMap<>(Currency.values().length);

    static {
        Arrays.stream(Currency.values()).forEach(e -> {
            VALUES.put(e.getValue(), e);
        });
    }


    public static Currency convertTo(Integer value) {
        return VALUES.get(value);
    }


}
