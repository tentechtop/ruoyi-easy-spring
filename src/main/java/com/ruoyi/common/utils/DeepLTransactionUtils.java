package com.ruoyi.common.utils;


import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.vo.TransactionResultVO;

import java.util.HashMap;
import java.util.Map;

public class DeepLTransactionUtils {


    public static TransactionResultVO getResult(String text, String sourceLang, String targetLang) {
        String apiUrl = "https://api.deeplx.org/translate";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", text);
        jsonObject.put("source_lang", sourceLang);
        jsonObject.put("target_lang", targetLang);

        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json;charset=UTF-8");

        int maxRetries = 10;
        int retryCount = 0;
        TransactionResultVO transactionResultVO = null;

        while (retryCount < maxRetries) {
            HttpResponse response = HttpRequest.post(apiUrl)
                    .headerMap(headers, false)
                    .body(String.valueOf(jsonObject))
                    .timeout(5 * 60 * 1000)
                    .execute();

            if (response.getStatus() == 200) {
                String body = response.body();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    transactionResultVO = objectMapper.readValue(body, TransactionResultVO.class);
                    break;
                } catch (JsonProcessingException e) {
                    throw new RuntimeException("Failed to parse response body", e);
                }
            } else {
                retryCount++;
                System.out.println("重试");
                try {
                    System.out.println("休眠5.5秒");
                    Thread.sleep(5500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (transactionResultVO != null) {
            return transactionResultVO;
        } else {
            throw new RuntimeException("Failed after multiple retries");
        }
    }

}
