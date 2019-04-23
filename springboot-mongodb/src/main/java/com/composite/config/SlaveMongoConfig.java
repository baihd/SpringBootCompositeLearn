package com.composite.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@Configuration
@ConfigurationProperties(prefix = "spring.data.mongodb.slave")
public class SlaveMongoConfig extends AbstractMongoConfig {

    @Override
    @Bean(name = "slaveMongoTemplate")
    public MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }
}
