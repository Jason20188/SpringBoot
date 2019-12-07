package com.test.dev.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.haofenqi.routes.base.response.ClientBaseResponse;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zhanghui18
 * @date 2019/10/22 14:42
 * @description json
 */
public class JsonUtil {


    public static <T> ClientBaseResponse<T> toBaseResponse(String json, Class<T> clazz) {
        return JSON.parseObject(json, new TypeReference<ClientBaseResponse<T>>(clazz) {
        });
    }

    public static <T> T toBean(Object obj, Class<T> clazz) {
        String json = (obj instanceof String) ? (String) obj : JSON.toJSONString(obj);
        return JSON.parseObject(json, clazz);
    }

    public static Map<String, String> toMap(Object obj) {
        String json = (obj instanceof String) ? (String) obj : JSON.toJSONString(obj);
        return JSON.parseObject(json, Map.class);
    }

    public static MultiValueMap<String, Object> toMultiMap(Object obj) {
        String json = (obj instanceof String) ? (String) obj : JSON.toJSONString(obj);
        MultiValueMap<String, Object> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.setAll(JSON.parseObject(json, Map.class));
        return multiValueMap;
    }

    public static <T> Map<String, ArrayList<T>> toMapList(Object obj, Class<T> clazz) {
        String json = (obj instanceof String) ? (String) obj : JSON.toJSONString(obj);
        return JSON.parseObject(json, new TypeReference<Map<String, ArrayList<T>>>(clazz) {
        });
    }

    public static String toJSONString(Object obj) {
        return (obj instanceof String) ? (String) obj : JSON.toJSONString(obj);
    }


}
