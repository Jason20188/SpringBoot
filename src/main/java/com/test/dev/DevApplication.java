package com.test.dev;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableRetry
@EnableTransactionManagement
@SpringBootApplication
@MapperScan(basePackages = {"com.test.dev.dao"})
public class DevApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevApplication.class, args);
    }

}
