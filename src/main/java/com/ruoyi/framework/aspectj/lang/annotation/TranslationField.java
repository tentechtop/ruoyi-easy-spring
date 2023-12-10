package com.ruoyi.framework.aspectj.lang.annotation;

import java.lang.annotation.*;

/**
 * 需要翻译的字段
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TranslationField {



}
