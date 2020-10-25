package com.someecho;


import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Copyright (C), 2019-2019, 企家有道网络技术（北京）有限公司
 * FileName: OkHttpHelper
 * package com.qjyd.seastar.util;
 *
 * @author changyu<changyu @ qijiayoudao.com>
 * @date 2019-07-24 14:55
 */
public class OkHttpHelper {

    private static Logger log = LoggerFactory.getLogger(OkHttpHelper.class);

    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .build();

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    /**
     * 根据url获取byte数组
     *
     * @param urls url集合
     * @return
     */
    public static byte[] getBytesByUrls(List<String> urls) {
        byte[] bytes = null;
        try (ByteArrayOutputStream stream = new ByteArrayOutputStream()) {
            for (String url : urls) {
                byte[] result = getBytesByUrl(url);
                if (result != null) {
                    stream.write(result);
                }
            }
            bytes = stream.toByteArray();
        } catch (Exception e) {
            log.error("根据url获取byte数组请求失败", e);
        }
        return bytes;
    }


    public static byte[] getBytesByUrl(String url) {
        byte[] bytes = null;
        try {
            final Request request = new Request.Builder()
                    .url(url)
                    .build();
            Call call = CLIENT.newCall(request);
            Response httpResponse = call.execute();
            bytes = httpResponse.body().bytes();
        } catch (Exception e) {
            log.error("根据url获取byte数组请求失败", e);
        }
        return bytes;
    }

    /**
     * post请求方式 参数为json格式
     *
     * @param url
     * @param jsonParams json格式请求参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url, String jsonParams) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonParams);
        Request request = new Request.Builder().url(url).post(body).build();
        return executeRequestBody(request);
    }

    /**
     * post请求方式 参数为表单格式
     *
     * @param url
     * @param formParams 表单格式请求参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> formParams) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();
        Request request = new Request.Builder().url(url).post(body).build();
        return executeRequestBody(request);
    }

    /**
     * post请求方式 参数为表单格式
     *
     * @param url
     * @param formParams 表单格式请求参数
     * @return
     * @throws IOException
     */
    public static String doPost(String url, Map<String, String> formParams, Map<String, String> header) throws IOException {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        Request.Builder request = new Request.Builder().url(url);
        for (Map.Entry<String, String> entry : header.entrySet()) {
            request.addHeader(entry.getKey(), entry.getValue());
        }
        RequestBody body = builder.build();
        return executeRequestBody(request.post(body).build());
    }

    /**
     * multipart 混合请求
     *
     * @param url
     * @param formParams     普通表单
     * @param requestBodyMap 文件表单
     * @return
     * @throws IOException
     */
    public static String doMultipartPost(String url, Map<String, String> formParams, Map<String, Object> requestBodyMap) throws IOException {
        MultipartBody.Builder builder = new MultipartBody.Builder().setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : formParams.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, Object> entry : requestBodyMap.entrySet()) {
            builder.addFormDataPart(entry.getKey(), "media", (RequestBody) entry.getValue());
        }
        RequestBody body = builder.build();
        return executeRequestBody(new Request.Builder().url(url).post(body).build());
    }

    /**
     * get
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String doGet(String url) throws IOException {
        Request request = new Request.Builder().url(url).build();
        return executeRequestBody(request);
    }


    /**
     * get 带参数
     *
     * @param url
     * @param params
     * @return
     * @throws IOException
     */
    public static String doGet(String url, Map<String, String> params) throws IOException {
        Request.Builder reqBuild = new Request.Builder();
        HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
        }
        reqBuild.url(urlBuilder.build());
        return executeRequestBody(reqBuild.build());
    }

    private static String executeRequestBody(Request body) throws IOException {
        Response response = CLIENT.newCall(body).execute();
        if (response.isSuccessful()) {
            return response.body().string();
        } else {
            throw new IOException("Unexpected code " + response);
        }
    }


}
