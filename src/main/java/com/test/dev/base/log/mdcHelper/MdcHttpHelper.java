
package com.test.dev.base.log.mdcHelper;

import com.test.dev.utils.DateUtil;
import com.test.dev.utils.GenerateUtil;
import org.slf4j.MDC;

class MdcHttpHelper extends MdcHelper {

    @Override
    public void clear() {
        MDC.remove(START_TIME);
        MDC.remove(TRANSACTION_ID);
        MDC.remove(REQUEST_URL);
        MDC.remove(REQUEST_PARAMS);
        MDC.remove(CLIENT_IP);
        MDC.remove(SERVER);
        MDC.remove(CALL_TIME);
        MDC.remove(CODE);
        MDC.remove(EXCEPTION);
    }

    public void requestMDC(String clientIp, String requestUrl, String requestParams, String server) {
        MDC.put(START_TIME, DateUtil.getPlusTime2(DateUtil.now()));
        MDC.put(TRANSACTION_ID, GenerateUtil.generateTransactionId());
        MDC.put(REQUEST_URL, requestUrl);
        MDC.put(REQUEST_PARAMS, requestParams);
        MDC.put(CLIENT_IP, clientIp);
        MDC.put(SERVER, server);
    }

    protected float computeCallTime() {
        String str = MDC.get(START_TIME);
        if (str == null) {
            return 0f;
        }

        try {
            return (System.currentTimeMillis() - DateUtil.getDateFromString(str).getTime()) / 1000.0f;
        } catch (Exception e) {
            return 0f;
        }
    }

    public void responseMDC(String code, String exception) {
        MDC.put(CALL_TIME, computeCallTime() + "");
        MDC.put(CODE, code);
        MDC.put(EXCEPTION, exception);
    }
}