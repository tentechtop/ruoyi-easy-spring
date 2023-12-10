package com.ruoyi.common;

/**
 * @author hcg
 * @date 2021-09-16
 */
public class ThreadLocals {
    /**
     * 当前的请求头 Accept-Language
     */
    public static final ThreadLocal<String> acceptLanguage = new ThreadLocal<>();

    /**
     * 浏览器指纹
     */
    public static final ThreadLocal<String> fingerprint = new ThreadLocal<>();

}
