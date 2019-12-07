package com.test.dev.base.log.layout;
import ch.qos.logback.classic.PatternLayout;
import com.test.dev.base.log.converter.*;

public class CustomPatternLayout extends PatternLayout {

    static {
        defaultConverterMap.put("timestamp", TimestampConverter.class.getName());
        defaultConverterMap.put("startTime", StartTimeConverter.class.getName());
        defaultConverterMap.put("transactionId", TransactionIdConverter.class.getName());
        defaultConverterMap.put("requestUrl", RequestUrlConverter.class.getName());
        defaultConverterMap.put("userId", UserIdConverter.class.getName());
        defaultConverterMap.put("callTime", CallTimeConverter.class.getName());
        defaultConverterMap.put("code", CodeConverter.class.getName());
        defaultConverterMap.put("clientIp", ClientIpConverter.class.getName());
        defaultConverterMap.put("requestParams", RequestParamsConverter.class.getName());
        defaultConverterMap.put("server", ServerConverter.class.getName());
        defaultConverterMap.put("exception", ExceptionConverter.class.getName());
    }
}