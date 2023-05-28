package com.example.graduation.utils;

import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.support.ClassicRequestBuilder;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送http请求
 *
 * @author fuhualiang
 * @author Varian
 */
public class HttpUtils {
    public enum RequestEnum {
        GET(0),
        POST(1),
        PUT(2),
        DELETE(3);
        public final int method;

        RequestEnum(int method) {
            this.method = method;
        }
    }

    public static String get(String url, Map<String, String> parameterMap, Map<String, String> bodyMap) {
        return doRequest(RequestEnum.GET, url, parameterMap, bodyMap);
    }

    public static String post(String url, Map<String, String> parameterMap, Map<String, String> bodyMap) {
        return doRequest(RequestEnum.POST, url, parameterMap, bodyMap);
    }

    public static String put(String url, Map<String, String> parameterMap, Map<String, String> bodyMap) {
        return doRequest(RequestEnum.PUT, url, parameterMap, bodyMap);
    }

    public static String delete(String url, Map<String, String> parameterMap, Map<String, String> bodyMap) {
        return doRequest(RequestEnum.DELETE, url, parameterMap, bodyMap);
    }

    private static String doRequest(RequestEnum method, String url, Map<String, String> parameterMap, Map<String, String> bodyMap) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            ClassicRequestBuilder classicRequestBuilder;
            // 请求方法
            if (method == RequestEnum.GET) {
                classicRequestBuilder = ClassicRequestBuilder.get(url);
            } else if (method == RequestEnum.POST) {
                classicRequestBuilder = ClassicRequestBuilder.post(url);
            } else if (method == RequestEnum.PUT) {
                classicRequestBuilder = ClassicRequestBuilder.put(url);
            } else if (method == RequestEnum.DELETE) {
                classicRequestBuilder = ClassicRequestBuilder.delete(url);
            } else {
                throw new RuntimeException("无请求方法！");
            }
            // 请求参数
            if (parameterMap != null) {
                for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                    classicRequestBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            // 请求体
            if (bodyMap != null) {
                List<NameValuePair> nvps = new ArrayList<>();
                for (Map.Entry<String, String> entry : bodyMap.entrySet()) {
                    nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
                }
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(nvps, StandardCharsets.UTF_8);
                classicRequestBuilder.setEntity(formEntity);
            }
            // 发送请求
            return httpclient.execute(classicRequestBuilder.build(), response -> {
                final HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return str;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String post(String url, Map<String, String> parameterMap, Map<String, String> bodyMap, File file) {
        try {
            CloseableHttpClient httpclient = HttpClients.createDefault();
            ClassicRequestBuilder classicRequestBuilder = ClassicRequestBuilder.post(url);
            // 请求参数
            if (parameterMap != null) {
                for (Map.Entry<String, String> entry : parameterMap.entrySet()) {
                    classicRequestBuilder.addParameter(entry.getKey(), entry.getValue());
                }
            }
            // 请求体
            MultipartEntityBuilder builder = MultipartEntityBuilder.create();
            if (bodyMap != null) {
                ContentType contentType = ContentType.create("multiple/form-data", StandardCharsets.UTF_8);
                for (Map.Entry<String, String> entry : bodyMap.entrySet()) {
                    builder.addTextBody(entry.getKey(), entry.getValue(), contentType);
                }
            }
            builder.addBinaryBody("pic", file, ContentType.MULTIPART_FORM_DATA, file.getName());
            classicRequestBuilder.setEntity(builder.build());
            // 发送请求
            return httpclient.execute(classicRequestBuilder.build(), response -> {
                final HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                EntityUtils.consume(entity);
                return str;
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("client_id", "2843458463");
        map.put("client_secret", "5bafa7257b6623c59d18648e2b1f146a");
        map.put("grant_type", "authorization_code");
        map.put("redirect_uri", "http://124.222.8.252:5173/");
        map.put("code", "b9eabf816ef06ab878af56e24f0baa25");
        HttpUtils.post("https://api.weibo.com/oauth2/access_token", map, null);
    }
}

