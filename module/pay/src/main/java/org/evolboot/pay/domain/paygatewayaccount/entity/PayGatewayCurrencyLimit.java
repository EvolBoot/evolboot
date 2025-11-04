package org.evolboot.pay.domain.paygatewayaccount.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.evolboot.shared.pay.Currency;

import java.math.BigDecimal;

/**
 * 支付网关货币限制
 *
 * @author evol
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "支付网关货币限制")
public class PayGatewayCurrencyLimit {

    @Schema(title = "货币")
    private Currency currency;

    @Schema(title = "代收最小金额")
    private BigDecimal minimumPayinAmount;

    @Schema(title = "代收最大金额")
    private BigDecimal maximumPayinAmount;

    @Schema(title = "代付最小金额")
    private BigDecimal minimumPayoutAmount;

    @Schema(title = "代付最大金额")
    private BigDecimal maximumPayoutAmount;

    /**
     * 校验代收金额是否在范围内
     *
     * @param amount 金额
     * @return true 在范围内，false 不在范围内
     */
    public boolean isPayinAmountValid(BigDecimal amount) {
        if (amount == null) {
            return false;
        }

        if (minimumPayinAmount != null && amount.compareTo(minimumPayinAmount) < 0) {
            return false;
        }

        if (maximumPayinAmount != null && amount.compareTo(maximumPayinAmount) > 0) {
            return false;
        }

        return true;
    }

    /**
     * 校验代付金额是否在范围内
     *
     * @param amount 金额
     * @return true 在范围内，false 不在范围内
     */
    public boolean isPayoutAmountValid(BigDecimal amount) {
        if (amount == null) {
            return false;
        }

        if (minimumPayoutAmount != null && amount.compareTo(minimumPayoutAmount) < 0) {
            return false;
        }

        if (maximumPayoutAmount != null && amount.compareTo(maximumPayoutAmount) > 0) {
            return false;
        }

        return true;
    }
}
