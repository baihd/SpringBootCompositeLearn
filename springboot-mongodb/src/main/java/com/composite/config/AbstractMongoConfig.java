package com.composite.config;

import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

public abstract class AbstractMongoConfig {
    private String host;

    private int port;

    private String database;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public MongoDbFactory mongoDbFactory() throws Exception {
        ServerAddress serverAddress = new ServerAddress(host, port);
        return new SimpleMongoDbFactory(new MongoClient(serverAddress), database);
    }

    public abstract MongoTemplate getMongoTemplate() throws Exception;

}
