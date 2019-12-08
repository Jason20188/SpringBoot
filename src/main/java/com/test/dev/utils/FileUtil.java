package com.test.dev.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * @author zhanghui18
 * @date 2019/11/6 15:55
 * @description 文件操作
 */
@Slf4j
public class FileUtil {


    /**
     * 读取文件内容
     *
     * @param path
     * @return
     */
    public static String readRSAFile(String path) {
        StringBuilder builder = new StringBuilder();
        path = getCertPath(path);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("--")) {
                    continue;
                }
                builder.append(line);
            }
        } catch (Exception e) {

        }
        return builder.toString();
    }

    /**
     * @param certPath
     * @return
     */
    public static String getCertPath(String certPath) {
        try {
            String resourcesPath = ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX).getPath();
            resourcesPath = resourcesPath.replace("test-classes", "classes");// 使用test单元测试转换
            return new StringBuilder(resourcesPath).append("certs/").append(certPath).toString();
        } catch (Exception e) {
            log.error("get cert path is error", e);
        }
        return "";
    }

}
