package org.evolboot.core.util;

import cn.hutool.http.HttpUtil;

import java.util.Map;

/**
 * @author evol
 */
public class ExtendHttpUtil {

    public static String post(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.post(urlString, paramMap);
    }

    public static String get(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.get(urlString, paramMap);
    }

    public static String get(String urlString) {
        return HttpUtil.get(urlString);
    }

    public static String postJson(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", "application/json")
                .body(JsonUtil.stringify(paramMap))
                .execute().body();
    }

    public static String post(String urlString, Map<String, Object> paramMap, int timeoutMs) {
        return HttpUtil.post(urlString, paramMap, timeoutMs);
    }


}
