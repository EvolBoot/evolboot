package org.evolboot.pay.remote.paymentclient.util;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.evolboot.core.util.JsonUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.TreeMap;

/**
 * @author evol
 */
@Slf4j
public class RequestUtil {


    public static TreeMap<String, Object> convertParamsToTreeMap(HttpServletRequest request) {
        TreeMap<String, Object> params = Maps.newTreeMap();
        String requestBody;
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (requestContentType != null) {
            //	只拦截 json 请求的参数
            if (requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || requestContentType.startsWith(MediaType.APPLICATION_XML_VALUE)) {
                requestBody = getRequestBody(request);
                return JsonUtil.parse(requestBody, TreeMap.class);
            }
        }
        return null;
    }


    public static String convertParamsToString(HttpServletRequest request) {
        String requestBody;
        String requestContentType = request.getHeader(HttpHeaders.CONTENT_TYPE);
        if (requestContentType != null) {
            //	只拦截 json 请求的参数
            if (requestContentType.startsWith(MediaType.APPLICATION_JSON_VALUE) || requestContentType.startsWith(MediaType.APPLICATION_XML_VALUE)) {
                requestBody = getRequestBody(request);
                return requestBody;
            }
        }
        return null;
    }


    private static String getRequestBody(HttpServletRequest request) {
        int contentLength = request.getContentLength();
        if (contentLength <= 0) {
            return "";
        }
        try {
            return IOUtils.toString(request.getReader());
        } catch (IOException e) {
            log.error("获取请求体失败", e);
            return "";
        }
    }

}
