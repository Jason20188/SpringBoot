package com.test.dev.base.log.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.test.dev.base.log.mdcHelper.MdcParams;
import org.slf4j.MDC;

public class TransactionIdConverter extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent le) {
        // 预留扩展
        return MDC.get(MdcParams.TRANSACTION_ID) + "";
    }
}