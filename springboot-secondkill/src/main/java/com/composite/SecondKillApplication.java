package com.composite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
@MapperScan("com.composite.dao")
public class SecondKillApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SecondKillApplication.class, args);
    }
}
