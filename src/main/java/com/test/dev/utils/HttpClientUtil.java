package com.test.dev.utils;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;

/**
 * @author zhanghui18
 * @date 2019/10/18 11:16
 * @description http 工具类
 */
@Slf4j
@Component
public class HttpClientUtil implements BeanFactoryAware {

    private static RestTemplate restTemplate;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        restTemplate = beanFactory.getBean(RestTemplate.class);
    }

    /**
     * 封装HttpEntity信息
     *
     * @param request
     * @return
     */
    private static HttpEntity<Object> entity(Object request, Map<String, String> headers, Boolean json) {
        HttpHeaders header = new HttpHeaders();
        if (json) {
            header.setContentType(APPLICATION_JSON_UTF8);
        } else {
            header.setContentType(APPLICATION_FORM_URLENCODED);
        }
        header.setAccept(ImmutableList.of(APPLICATION_JSON_UTF8));
        if (!CollectionUtils.isEmpty(headers)) {
            headers.forEach((key, val) -> header.add(key, val));
        }
        return new HttpEntity<>(request, header);
    }

    public static <T> T get(String url, HttpEntity<?> requestEntity, Class<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, requestEntity, responseType);
        validateStatus(res, url);
        return res.getBody();
    }

    public static <T> T get(String url, Class<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T get(String url, ParameterizedTypeReference<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType, uriVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T get(String url, Class<T> responseType, Map<String, ?> urlVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType, urlVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T get(String url, ParameterizedTypeReference<T> responseType, Map<String, ?> urlVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType, urlVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T get(String url, Class<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T get(String url, ParameterizedTypeReference<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.GET, null, responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T post(String url, Object request, Class<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = restTemplate.postForEntity(url, entity(request, null, true), responseType, uriVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T post(String url, MultiValueMap<String, Object> multiValueMap, Class<T> responseType, Map<String, String> headers) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.POST, entity(multiValueMap, headers, false), responseType);
        validateStatus(res, url);
        return res.getBody();
    }

    public static <T> T post(String url, HttpHeaders headers, Class<T> responseType) {
        ResponseEntity<T> res = restTemplate.postForEntity(url, new HttpEntity<>(null, headers), responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T post(String url, Object request, Class<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.POST, entity(request, null, true), responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T post(String url, Object request, ParameterizedTypeReference<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.POST, entity(request, null, true), responseType);
        validateStatus(res, url);
        return res.getBody();
    }

    public static <T> T post(String url, Object request, Class<T> responseType, Map<String, String> header) {
        ResponseEntity<T> res = restTemplate.postForEntity(url, entity(request, header, true), responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T put(String url, HttpEntity<?> requestEntity, Class<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, responseType);
        validateStatus(res, url);
        return res.getBody();
    }

    public static void put(String url, Object[] uriVariables) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.PUT, null, String.class, uriVariables);
        validateStatus(res, url);
    }


    public static <T> T put(String url, Object request, ParameterizedTypeReference<T> responseType) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.PUT, entity(request, null, true), responseType);
        validateStatus(res, url);
        return res.getBody();
    }


    public static <T> T jsonPut(String url, Object request, ParameterizedTypeReference<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.PUT, entity(request, null, true), responseType, uriVariables);
        validateStatus(res, url);
        return res.getBody();
    }


    public static void put(String url, Object request, Object[] uriVariables) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.PUT, entity(request, null, true), String.class, uriVariables);
        validateStatus(res, url);
    }


    public static void put(String url, Object request, Map<String, ?> urlVariables) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.PUT, entity(request, null, true), String.class, urlVariables);
        validateStatus(res, url);
    }


    public static void put(String url, Object request) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.PUT, entity(request, null, true), String.class);
        validateStatus(res, url);
    }


    public static void delete(String url) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class);
        validateStatus(res, url);
    }


    public static <T> T delete(String url, ParameterizedTypeReference<T> responseType, Object[] uriVariables) {
        ResponseEntity<T> res = restTemplate.exchange(url, HttpMethod.DELETE, null, responseType, uriVariables);
        validateStatus(res, url);
        return res.getBody();
    }

    /**
     * delete操作
     *
     * @param url
     * @param urlVariables
     */
    public static void delete(String url, Map<String, ?> urlVariables) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, urlVariables);
        validateStatus(res, url);
    }

    public static void delete(String url, Object[] uriVariables) {
        ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.DELETE, null, String.class, uriVariables);
        validateStatus(res, url);
    }

    private static void validateStatus(ResponseEntity responseEntity, String url) {
        if (!Objects.equals(responseEntity.getStatusCode(), HttpStatus.OK)) {
            Object body = responseEntity.getBody();
            log.error("Failed: HTTP error code:{} , error body: {}, request url is :{}", responseEntity.getStatusCode(), body, url);
            Assert.isTrue(false, body.toString());
        }
    }
}
