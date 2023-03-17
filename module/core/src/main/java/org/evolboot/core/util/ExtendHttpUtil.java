package org.evolboot.core.util;

import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author evol
 */
public class ExtendHttpUtil {

    public static String post(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.post(urlString, paramMap);
    }


    public static String get(String urlString) {
        return HttpUtil.get(urlString);
    }



    public static String get(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.get(urlString, paramMap);
    }



    public static String postJson(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", "application/json")
                .body(JsonUtil.stringify(paramMap))
                .execute().body();
    }



    public static HttpResponse postJsonReturnResponse(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", "application/json")
                .body(JsonUtil.stringify(paramMap))
                .execute();
    }


    public static String post(String urlString, Map<String, Object> paramMap, int timeoutMs) {
        return HttpUtil.post(urlString, paramMap, timeoutMs);
    }

    /**
     * URL 增加参数
     *
     * @param uri
     * @param appendQuery
     * @return
     * @throws URISyntaxException
     */
    public static String appendUri(String uri, String appendQuery) {
        try {
            URI oldUri = new URI(uri);
            String newQuery = oldUri.getQuery();
            if (newQuery == null) {
                newQuery = appendQuery;
            } else {
                newQuery += "&" + appendQuery;
            }

            URI newUri = new URI(oldUri.getScheme(), oldUri.getAuthority(),
                    oldUri.getPath(), newQuery, oldUri.getFragment());

            return newUri.toString();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return uri;
    }

}
