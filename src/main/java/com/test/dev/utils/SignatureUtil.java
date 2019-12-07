package com.test.dev.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMKeyPair;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhanghui18
 * @date 2019/10/10 20:46
 * @description 签名
 */
public class SignatureUtil {

    /**
     * key值按字母进行排序
     *
     * @param parameterMap 参数
     * @param requestBody  body
     * @param ts           时间
     * @return
     */
    public static String buildStringFromRequest(Map<String, String[]> parameterMap, String requestBody, long ts) {
        String raw = "";
        if (parameterMap != null && parameterMap.size() > 0) {
            raw = parameterMap.entrySet().stream()
                    .filter(it -> it.getValue().length > 0) // 忽略无值参数
                    .sorted((e1, e2) -> e1.getKey().compareTo(e2.getKey())) // 按key排序
                    .map(e -> e.getKey() + '=' + e.getValue()[0]) // 对于多值参数，只取第一个值
                    .collect(Collectors.joining("&"));
        }

        if (StringUtils.isNotBlank(requestBody)) {
            raw += '|' + requestBody;
        }

        if (StringUtils.isNotBlank(raw) && ts > 0) {
            // 若为false说明既无parameter又无body，没必要拼接
            raw += "|" + ts;
        }

        return raw;
    }

    /**
     * 生成待签名串
     *
     * @param jsonObject
     * @return
     */
    private static String buildSignSrc(JSONObject jsonObject) {
        // 按照key做首字母升序排列
        List<String> keys = new ArrayList<>(jsonObject.keySet());
        keys.sort(String.CASE_INSENSITIVE_ORDER);
        StringBuilder content = new StringBuilder();
        for (String key : keys) {
            String value = jsonObject.getString(key);
            // 空串值不参与签名
            if ("sign".equals(key) || org.apache.commons.lang3.StringUtils.isBlank(value)) {
                continue;
            }

            // 对内部对象进行排序
            try {
                JSONObject json = JSONObject.parseObject(value);
                value = JSONObject.toJSONString(json, SerializerFeature.MapSortField);
            } catch (Exception e) {
            }

            try {
                JSONArray jsonArray = JSONArray.parseArray(value);
                value = JSONArray.toJSONString(jsonArray, SerializerFeature.MapSortField);
            } catch (Exception e) {
            }

            content.append("&" + key + "=" + value);
        }

        String signSrc = content.toString();
        if (signSrc.startsWith("&")) {
            signSrc = signSrc.replaceFirst("&", "");
        }
        return signSrc;
    }


    public static String sign(Object value, String suffix) {
        String param = buildSignSrc(JSON.parseObject(JSON.toJSONString(value)));
        return MD5Util.md5Str(param + suffix).toLowerCase();
    }

    /**
     * 账务 签名
     *
     * @param raw    url params
     * @param suffix 秘钥
     * @return
     */
    public static String signMd5(String raw, String suffix) {
        String sign = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest((raw + suffix).getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, messageDigest);
            sign = number.toString(16);
            sign = sign.length() < 32 ? "0" + sign : sign;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return sign;
    }

    /**
     * 共债签名
     *
     * @param raw        url params
     * @param privateKey 私钥
     * @return
     */
    public static String digest(String raw, PrivateKey privateKey) {
        try {
            Signature signature = Signature.getInstance("SHA384withRSA");
            signature.initSign(privateKey);
            signature.update(raw.getBytes());
            return Base64.encodeBase64String(signature.sign());
        } catch (InvalidKeyException | SignatureException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static PrivateKey getPrivateKey(String filename) {
        try {
            File f = new File(filename);
            PEMParser pemParser = new PEMParser(new FileReader(f));
            Object object = pemParser.readObject();
            JcaPEMKeyConverter converter = new JcaPEMKeyConverter().setProvider(new BouncyCastleProvider());
            KeyPair keyPair = converter.getKeyPair((PEMKeyPair) object);
            return keyPair.getPrivate();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
