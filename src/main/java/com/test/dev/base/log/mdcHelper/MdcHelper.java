package com.test.dev.base.log.mdcHelper;

import org.slf4j.MDC;

/**
 * Created with IntelliJ IDEA.
 * User: shidong01
 * Date: 2019-10-25
 * Time: 15:23
 * Description: No Description
 */
public abstract class MdcHelper extends MdcParams {

    private static MdcHelper mdcHttpHelper = new MdcHttpHelper();
    private static MdcHelper mdcThirdHelper = new MdcThirdHelper();

    public static MdcHelper http() {
        return mdcHttpHelper;
    }

    public static MdcHelper third() {
        return mdcThirdHelper;
    }

    public abstract void clear();

    public abstract void requestMDC(String clientIp, String requestUrl, String requestParams, String server);

    protected abstract float computeCallTime();

    public abstract void responseMDC(String code, String exception);
}