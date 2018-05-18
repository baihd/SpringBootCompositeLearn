package com.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootMybatisTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootMybatisTransactionApplication.class, args);
    }
}
