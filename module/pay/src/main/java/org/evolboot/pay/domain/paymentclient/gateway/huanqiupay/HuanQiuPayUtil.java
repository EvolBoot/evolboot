package org.evolboot.pay.domain.paymentclient.gateway.huanqiupay;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.evolboot.core.util.Assert;
import org.evolboot.core.util.MD5Util;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author evol
 */
@Slf4j
public class HuanQiuPayUtil {

    public static String getDate() {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateformat.format(new Date());
    }

    public static String sort(TreeMap<String, Object> params, String secretKey) {
        StringBuffer sb = new StringBuffer();
        Set<Map.Entry<String, Object>> entries = params.entrySet();
        entries.forEach(entrie -> {
            sb.append(entrie.getKey()).append("=").append(entrie.getValue()).append("&");
        });
        sb.delete(sb.length() - 1, sb.length());
        sb.append("&").append(secretKey);
        return sb.toString();
    }

    public static void checkSign(Map<String, String> requestParams, String secretKey) {
        TreeMap<String, Object> map = Maps.newTreeMap();
        String sign = requestParams.get("sign");
        requestParams.remove("sign");
        requestParams.remove("sign_type");
        map.putAll(requestParams);
        String mySign = sign(map, secretKey);
        log.info("回调的签名:{},我的签名:{}", sign, mySign);
        Assert.isTrue(mySign.equalsIgnoreCase(sign), "签名错误");
    }

    public static String sign(TreeMap<String, Object> params, String secretKey) {
        return MD5Util.md5(sort(params, secretKey)).toUpperCase();
    }


}
