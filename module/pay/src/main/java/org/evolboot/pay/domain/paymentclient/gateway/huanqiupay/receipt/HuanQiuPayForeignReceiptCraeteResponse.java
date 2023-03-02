package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay.receipt;

import org.evolboot.core.util.ExtendObjects;

/**
 * @author evol
 */
public class HuanQiuPayForeignReceiptCraeteResponse {

    public boolean isOk() {
        if (ExtendObjects.isNull(this.getData())) {
            return false;
        }
        return "成功".equals(this.getData().getStatus());
    }

    public String getStatus() {
        return this.getData().getStatus();
    }

    public String getPayUrl() {
        return getData().getTrade_qrcode();
    }

    public String getForeignOrderId() {
        return null;
    }

    /**
     * result : success
     * code : MSG_OK
     * msg : 操作成功
     * data : {"mer_id":"xt491112216514","businessnumber":"1637217749508","status":"成功","transactiondate":"2021-11-18 14:43:04","amount":"10000.00","real_amount":"10000.00","transactiontype":"代收","inputdate":"2021-11-18 14:43:04","trade_qrcode":"https://api.stars-pay.com/api/order/jump?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmRlcm51bWJlciI6IlRQMTYzNzIxNzc1MFdaQ0pHIiwiZW50ZXJwcmlzZV9pZCI6N30.S1R5vGirZWAnooTM6QjyfbFJdtMdSoRd_F9OTVni4-o&language=th","orderinfo":"","remark":""}
     */

    private String result;
    private String code;
    private String msg;
    private DataBean data;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * mer_id : xt491112216514
         * businessnumber : 1637217749508
         * status : 成功
         * transactiondate : 2021-11-18 14:43:04
         * amount : 10000.00
         * real_amount : 10000.00
         * transactiontype : 代收
         * inputdate : 2021-11-18 14:43:04
         * trade_qrcode : https://api.stars-pay.com/api/order/jump?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJvcmRlcm51bWJlciI6IlRQMTYzNzIxNzc1MFdaQ0pHIiwiZW50ZXJwcmlzZV9pZCI6N30.S1R5vGirZWAnooTM6QjyfbFJdtMdSoRd_F9OTVni4-o&language=th
         * orderinfo :
         * remark :
         */

        private String mer_id;
        private String businessnumber;
        private String status;
        private String transactiondate;
        private String amount;
        private String real_amount;
        private String transactiontype;
        private String inputdate;
        private String trade_qrcode;
        private String orderinfo;
        private String remark;

        public String getMer_id() {
            return mer_id;
        }

        public void setMer_id(String mer_id) {
            this.mer_id = mer_id;
        }

        public String getBusinessnumber() {
            return businessnumber;
        }

        public void setBusinessnumber(String businessnumber) {
            this.businessnumber = businessnumber;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTransactiondate() {
            return transactiondate;
        }

        public void setTransactiondate(String transactiondate) {
            this.transactiondate = transactiondate;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getReal_amount() {
            return real_amount;
        }

        public void setReal_amount(String real_amount) {
            this.real_amount = real_amount;
        }

        public String getTransactiontype() {
            return transactiontype;
        }

        public void setTransactiontype(String transactiontype) {
            this.transactiontype = transactiontype;
        }

        public String getInputdate() {
            return inputdate;
        }

        public void setInputdate(String inputdate) {
            this.inputdate = inputdate;
        }

        public String getTrade_qrcode() {
            return trade_qrcode;
        }

        public void setTrade_qrcode(String trade_qrcode) {
            this.trade_qrcode = trade_qrcode;
        }

        public String getOrderinfo() {
            return orderinfo;
        }

        public void setOrderinfo(String orderinfo) {
            this.orderinfo = orderinfo;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }
    }
}
