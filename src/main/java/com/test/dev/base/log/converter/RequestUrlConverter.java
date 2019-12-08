package com.test.dev.base.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.test.dev.base.log.mdcHelper.MdcParams;
import org.slf4j.MDC;

public class RequestUrlConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent le) {
        return null != MDC.get(MdcParams.THIRD_REQUEST_URL) ?
                MDC.get(MdcParams.THIRD_REQUEST_URL) + "" : MDC.get(MdcParams.REQUEST_URL) + "";
    }
}