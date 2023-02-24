package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.evolboot.pay.domain.paymentclient.released.ReleasedRequestResult;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author evol
 */
@NoArgsConstructor
@Data
@Setter
@Getter
public class HuanQiuPayReleasedRequestResult implements ReleasedRequestResult {

    //{"result":"success","code":"MSG_OK","msg":"操作成功","data":{"mer_id":"xt171219211449","businessnumber":"1639130417302","status":"处理中","transactiondate":"2021-12-10 18:00:33","amount":2000000,"transactiontype":"代付","inputdate":"2021-12-10 18:00:33","remark":"处理中","cash_type":1,"sign":"818E053870355BA630D104E18890DF16","sign_type":"md5"}}

    public boolean isOk() {
        return "MSG_OK".equalsIgnoreCase(code)
                && "success".equalsIgnoreCase(result)
                && "处理中".equals(data.getStatus());
    }

    @Override
    public String getStatus() {
        return code;
    }

    @Override
    public String getForeignOrderId() {
        return null;
    }

    @JsonProperty("result")
    private String result;
    @JsonProperty("code")
    private String code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private DataDTO data;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("mer_id")
        private String merId;
        @JsonProperty("businessnumber")
        private String businessnumber;
        @JsonProperty("status")
        private String status;
        @JsonProperty("transactiondate")
        private String transactiondate;
        @JsonProperty("amount")
        private Integer amount;
        @JsonProperty("transactiontype")
        private String transactiontype;
        @JsonProperty("inputdate")
        private String inputdate;
        @JsonProperty("remark")
        private String remark;
        @JsonProperty("cash_type")
        private Integer cashType;
        @JsonProperty("sign")
        private String sign;
        @JsonProperty("sign_type")
        private String signType;
    }
}
