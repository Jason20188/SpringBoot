package com.test.dev.utils;

import org.springframework.aop.framework.AopContext;

public class AopUtil {

    public static <T> T getCurProxy(T t) {
        return (T) AopContext.currentProxy();
    }
}