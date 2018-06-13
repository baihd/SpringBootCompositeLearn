package com.composite.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//某个class位于类路径上，才会实例化一个Bean
@ConditionalOnClass(SnowflakeIdWorker.class)
@EnableConfigurationProperties(SnowflakeIdWorkerProperties.class)
public class SnowflakeIdWorkerAutoConfigure {

    @Autowired
    private SnowflakeIdWorkerProperties snowflakeIdWorkerProperties;

    //仅仅在当前上下文中不存在某个对象时，才会实例化一个Bean
    @Bean
    @ConditionalOnMissingBean
    SnowflakeIdWorker snowflakeIdWorker() {
        return new SnowflakeIdWorker(snowflakeIdWorkerProperties.getWorkerId(),
                snowflakeIdWorkerProperties.getDataCenterId());
    }
}
