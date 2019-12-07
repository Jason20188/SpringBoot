package com.test.dev.base.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class UserIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent le) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (null == attributes) {
            return "0";
        }
        HttpServletRequest request = attributes.getRequest();
        if (null == request) {
            return "0";
        }
        String userId = request.getHeader("userKey");//预留userId字段
        return null == userId ? "0" : userId;
    }
}