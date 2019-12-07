package com.test.dev.base.log.mdcHelper;


public class MdcParams {

    public static String START_TIME = "startTime";              //请求开始时间
    public static String TRANSACTION_ID = "transactionId";      //事务ID
    public static String REQUEST_URL = "requestUrl";            //请求URL
    public static String CALL_TIME = "callTime";                //响应时间
    public static String CODE = "code";                        //应答码
    public static String CLIENT_IP = "clientIp";                //请求客户端IP
    public static String REQUEST_PARAMS = "requestParams";      //请求参数
    public static String SERVER = "server";                    //服务器信息
    public static String EXCEPTION = "exception";              //异常信息

    // 三方请求无事务ID,遵从父请求事务ID
    public static String THIRD_START_TIME = "thirdStartTime";              //请求开始时间
    public static String THIRD_REQUEST_URL = "thirdRequestUrl";            //请求URL
    public static String THIRD_CALL_TIME = "thirdCallTime";                //响应时间
    public static String THIRD_CODE = "thirdCode";                        //应答码
    public static String THIRD_CLIENT_IP = "thirdClientIp";                //请求客户端IP
    public static String THIRD_REQUEST_PARAMS = "thirdRequestParams";      //请求参数
    public static String THIRD_SERVER = "thirdServer";                    //服务器信息
    public static String THIRD_EXCEPTION = "thirdException";              //异常信息
}