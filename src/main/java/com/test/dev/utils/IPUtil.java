package com.test.dev.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Create by shidong on 2018/6/4
 */
public class IPUtil {

    /**
     * 获取请求的ip地址
     * @param request
     * @return
     */
    public static String getIp(HttpServletRequest request){
        String ip = request.getHeader("X-Real-IP");
        return ip;
    }

    public static String getHostIp(){
        String ip = "127.0.0.1";
//        try {
//            InetAddress localHost = InetAddress.getLocalHost();
//            if(localHost!=null){
//                ip = localHost.getHostAddress();
//            }
//        } catch (UnknownHostException e) {}

        return ip;
    }

    //long型的ip转为String
    public static String longToIp(long i) {
        return ((i >> 24) & 0xFF) +
                "." + ((i >> 16) & 0xFF) +
                "." + ((i >> 8) & 0xFF) +
                "." + (i & 0xFF);

    }

    //String类型的Ip转为Long
    public static long ipToLong(String ipAddress) {
        long result = 0;
        String[] ipAddressInArray = ipAddress.split("\\.");

        for (int i = 3; i >= 0; i--) {
            long ip = Long.parseLong(ipAddressInArray[3 - i]);
            result |= ip << (i * 8);
        }

        return result;
    }

}
