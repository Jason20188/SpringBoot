
package com.test.dev.base.log.mdcHelper;

import com.test.dev.utils.DateUtil;
import org.slf4j.MDC;

/**
 * Description: 应用内部三方请求日志
 */
class MdcThirdHelper extends MdcHelper {

    @Override
    public void clear() {
        MDC.remove(THIRD_START_TIME);
        MDC.remove(THIRD_REQUEST_URL);
        MDC.remove(THIRD_REQUEST_PARAMS);
        MDC.remove(THIRD_CLIENT_IP);
        MDC.remove(THIRD_SERVER);
        MDC.remove(THIRD_CALL_TIME);
        MDC.remove(THIRD_CODE);
        MDC.remove(THIRD_EXCEPTION);
    }

    @Override
    public void requestMDC(String clientIp, String requestUrl, String requestParams, String server) {
        MDC.put(THIRD_START_TIME, DateUtil.getPlusTime2(DateUtil.now()));
        MDC.put(THIRD_REQUEST_URL, requestUrl);
        MDC.put(THIRD_REQUEST_PARAMS, requestParams);
        MDC.put(THIRD_CLIENT_IP, clientIp);
        MDC.put(THIRD_SERVER, server);
    }

    @Override
    protected float computeCallTime() {
        String str = MDC.get(THIRD_START_TIME);
        if (str == null) {
            return 0f;
        }

        try {
            return (System.currentTimeMillis() - DateUtil.getDateFromString(str).getTime()) / 1000.0f;
        } catch (Exception e) {
            return 0f;
        }
    }

    @Override
    public void responseMDC(String code, String exception) {
        MDC.put(THIRD_CALL_TIME, computeCallTime() + "");
        MDC.put(THIRD_CODE, code);
        MDC.put(THIRD_EXCEPTION, exception);
    }
}