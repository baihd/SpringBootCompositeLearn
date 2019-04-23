package com.composite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.master")
public class MasterMongoConfig extends AbstractMongoConfig {

    @Primary
    @Bean(name = "masterMongoTemplate")
    @Override
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

}
