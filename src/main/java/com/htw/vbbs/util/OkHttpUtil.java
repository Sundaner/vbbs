package com.htw.vbbs.util;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Slf4j
@Component
public class OkHttpUtil {

    private static OkHttpClient okHttpClient;

    @Autowired
    public OkHttpUtil(OkHttpClient okHttpClient) {
        OkHttpUtil.okHttpClient = okHttpClient;
    }

    /**
     * get
     *
     * @param url 请求的url
     * @param queries 请求的参数，在浏览器？后面的数据，没有可以传null
     * @return
     */
    public static String get(String url, Map<String, String> queries) {
        String responseBody = "";
        StringBuffer sb = new StringBuffer(url);
        if (MapUtils.isNotEmpty(queries)) {
            boolean firstFlag = true;
            Iterator iterator = queries.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry entry = (Map.Entry<String, String>) iterator.next();
                if (firstFlag) {
                    sb.append("?" + entry.getKey() + "=" + entry.getValue());
                    firstFlag = false;
                } else {
                    sb.append("&" + entry.getKey() + "=" + entry.getValue());
                }
            }
        }
        Request request = new Request.Builder().url(sb.toString()).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            //int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 put error >> ex = {}", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    /**
     * Post请求发送JSON数据....{"name":"zhangsan","pwd":"123456"} 参数一：请求Url 参数二：请求的JSON
     * 参数三：请求回调
     */
    public static String postJsonParams(String url, String jsonParams) {
        String responseBody = "";
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonParams);
        Request request = new Request.Builder().url(url).post(requestBody).build();
        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            //            int status = response.code();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            log.error("okhttp3 post error >> ex = {}", e);
        } finally {
            if (response != null) {
                response.close();
            }
        }
        return responseBody;
    }

    public String getLrcTXT(String url){
        Request request = new Request.Builder().url(url).build();
        String lrc = null;
        try {
            Response response = okHttpClient.newCall(request).execute();
            lrc = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lrc;
    }

//    public static void main(String[] args) {
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url("http://qukufile2.qianqian.com/data2/lrc/c2a60657ff56a87a3c4ddaf2ddf3f264/675961655/675961655.txt")
//                .build();
//        client.newCall(request).enqueue(new Callback(){
//            @Override
//            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
//                if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
//
//                Headers responseHeaders = response.headers();
//                for (int i = 0, size = responseHeaders.size(); i < size; i++) {
//                    System.out.println(responseHeaders.name(i) + ": " + responseHeaders.value(i));
//                }
//                System.out.println(response.body().string());
//            }
//
//            @Override
//            public void onFailure(Call call, @NotNull IOException e) {
//                e.printStackTrace();
//            }
//        });
//    }

}
