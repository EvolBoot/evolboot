package org.evolboot.sms.domain.miaodi;

import cn.hutool.crypto.digest.MD5;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * 未完成,使用时需要重构
 *
 * @author evol
 */
@Getter
@Slf4j
public class MiaoDiHttp {

    private String appid = "";
    private String baseUrl = "";
    private String accountSid = "";
    private String authToken = "";
    private String version = "";


    public MiaoDiHttp(String appid, String baseUrl, String accountSid, String authToken, String version) {
        this.appid = appid;
        this.baseUrl = baseUrl;
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.version = version;
    }


    public MiaoDiSmsResponse sendCode(String mobile, String code, String templateId) {
        String[] sigParams = getParams();
/*
        Map<Object, Object> request =
                MapUtil.builder()
                        .put("accountSid", accountSid)
                        .put("templateid", templateId)
                        .put("param", code)
                        .put("to", mobile)
                        .put("sig", sigParams[0])
                        .put("timestamp", sigParams[1])
                        .build();
*/

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        var param = new LinkedMultiValueMap<String, String>();
        param.add("accountSid", accountSid);
        param.add("templateid", templateId);
        param.add("param", code);
        param.add("to", mobile);
        param.add("sig", sigParams[0]);
        param.add("timestamp", sigParams[1]);
        var request = new HttpEntity<MultiValueMap<String, String>>(param, headers);
        ResponseEntity<MiaoDiSmsResponse> responseEntity = new RestTemplate().postForEntity(baseUrl, request, MiaoDiSmsResponse.class);
        log.info("手机号:{},请求状态:{},返回值:{}", mobile, responseEntity.getStatusCode(), responseEntity.getBody());
        return responseEntity.getBody();
    }

    private String[] getParams() {
        String yyyyMMddHHmmss = System.currentTimeMillis() + "";
        String accId = accountSid;
        String token = authToken;
        String sig = MD5.create().digestHex(accId + token + yyyyMMddHHmmss);
        return new String[]{sig, yyyyMMddHHmmss};
    }
}
