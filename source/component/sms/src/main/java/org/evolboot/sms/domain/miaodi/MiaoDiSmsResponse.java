package org.evolboot.sms.domain.miaodi;

import lombok.ToString;

import java.util.List;

/**
 * @author evol
 */
@ToString
public class MiaoDiSmsResponse {

    public final static String SUCCESS = "0000";
    public final static String FAULT_MOBILE = "0025";

    public String respCode;
    public Integer failCount;
    public List<FailPhone> failList;
    public String smsId;

    public static class FailPhone {
        public String phone;
        public String respCode;

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getRespCode() {
            return respCode;
        }

        public void setRespCode(String respCode) {
            this.respCode = respCode;
        }
    }

    public boolean isSuccess() {
        return SUCCESS.equals(respCode);
    }

    public boolean isFail() {
        return !isSuccess();
    }

    public boolean isNotMobile() {
        return FAULT_MOBILE.equals(respCode);
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public Integer getFailCount() {
        return failCount;
    }

    public void setFailCount(Integer failCount) {
        this.failCount = failCount;
    }

    public List<FailPhone> getFailList() {
        return failList;
    }

    public void setFailList(List<FailPhone> failList) {
        this.failList = failList;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }
}
