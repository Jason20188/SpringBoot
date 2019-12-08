package com.test.dev.utils;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: shidong01
 * Date: 2018/6/21
 * Time: 14:58
 * Description: No Description
 */
public class GenerateUtil {

    public static String generateTransactionId() {
        return generateRandomStrWithTime();
    }

    public static String generateFinanceSerialNo() {
        return generateRandomStrWithTime();
    }

    public static String generateWithdrawOrderId() {
        return generateRandomStrWithTime().replaceAll("_", "").replaceAll("-", "").substring(0, 32);
    }

    public static String generateRandomStrWithTime() {
        // transactionId : CurrentTimeMillis_UUID
        StringBuilder transactionId = new StringBuilder();
        transactionId.append(System.currentTimeMillis())
                .append("_").append(UUID.randomUUID().toString());
        return transactionId.toString();
    }

    public static void main(String[] args) {

        String s = "1558526211900_3f6aae72-e48f-472b";
        for (int i = 1; i <= 10; i++) {
            System.out.println(generateWithdrawOrderId());
        }
    }
}