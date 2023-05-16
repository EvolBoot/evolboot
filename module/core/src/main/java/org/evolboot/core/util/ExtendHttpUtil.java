package org.evolboot.core.util;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import org.springframework.util.MimeTypeUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * @author evol
 */
public class ExtendHttpUtil {


    /**
     * post form,表单
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param timeoutMs 超时时间
     * @return
     */
    public static String post(String urlString, Map<String, Object> paramMap, int timeoutMs) {
        return HttpUtil.post(urlString, paramMap, timeoutMs);
    }


    /**
     * post form,表单
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @param timeoutMs 超时时间
     * @return
     */
    public static String post(String urlString, Map<String, Object> paramMap, Map<String, String> header, int timeoutMs) {
        return HttpRequest
                .post(urlString)
                .form(paramMap)
                .headerMap(header, true)
                .timeout(timeoutMs)
                .execute()
                .body();
    }


    /**
     * post form  表单
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @return 结果字符串
     */
    public static String post(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.post(urlString, paramMap);
    }

    /**
     * post form 表单
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @return 结果字符串
     */
    public static String post(String urlString, Map<String, Object> paramMap, Map<String, String> header) {
        return HttpRequest
                .post(urlString)
                .form(paramMap)
                .headerMap(header, true)
                .execute()
                .body();

    }

    /**
     * get 方式
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @return 结果字符串
     */
    public static String get(String urlString, Map<String, Object> paramMap, Map<String, String> header) {
        return HttpRequest
                .get(urlString)
                .form(paramMap)
                .headerMap(header, true)
                .execute()
                .body();

    }

    /**
     * get 方式
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @param timeoutMs 超时时间
     * @return 结果字符串
     */
    public static String get(String urlString, Map<String, Object> paramMap, Map<String, String> header, int timeoutMs) {
        return HttpRequest
                .get(urlString)
                .form(paramMap)
                .headerMap(header, true)
                .timeout(timeoutMs)
                .execute()
                .body();

    }


    /**
     * 纯get,有参数自己拼接
     *
     * @param urlString URL地址
     * @return 结果字符串
     */
    public static String get(String urlString) {
        return HttpUtil.get(urlString);
    }

    /**
     * GET 方式,将map参数转为 get 地址
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @return
     */
    public static String get(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.get(urlString, paramMap);
    }


    /**
     * post json ,以json的
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @return 结果字符串
     */
    public static String postJson(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .body(JsonUtil.stringify(paramMap))
                .execute().body();
    }

    /**
     * post json
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @return 结果字符串
     */
    public static String postJson(String urlString, Map<String, Object> paramMap, Map<String, String> header) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .headerMap(header, true)
                .body(JsonUtil.stringify(paramMap))
                .execute().body();
    }


    /**
     * post json
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @param header    header头
     * @param timeoutMs 超时时间
     * @return 结果字符串
     */
    public static String postJson(String urlString, Map<String, Object> paramMap, Map<String, String> header, int timeoutMs) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .headerMap(header, true)
                .timeout(timeoutMs)
                .body(JsonUtil.stringify(paramMap))
                .execute().body();
    }


    /**
     * post json,返回HttpResponse
     *
     * @param urlString URL地址
     * @param paramMap  参数
     * @return 结果字符串
     */
    public static HttpResponse postJsonReturnResponse(String urlString, Map<String, Object> paramMap) {
        return HttpUtil.createPost(urlString)
                .header("Content-Type", MimeTypeUtils.APPLICATION_JSON_VALUE)
                .body(JsonUtil.stringify(paramMap))
                .execute();
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
