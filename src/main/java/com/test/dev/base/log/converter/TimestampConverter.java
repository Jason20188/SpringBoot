package com.test.dev.base.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.test.dev.utils.DateUtil;

public class TimestampConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent le) {
        return DateUtil.now().getTime() + "";
    }
}