package com.composite.config;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class Configs {

    @Value("classpath:config/tomcat.json")
    File serverConfigJson;

    public ServerCfg readServerConfig() throws IOException {
        return new ObjectMapper().readValue(serverConfigJson, ServerCfg.class);
    }

    public static void main(String[] args) {
        Configs demo = new Configs();
        String json = toJson(demo.createTestBean());
        System.out.println(json);
    }

    private static String toJson(Object object){
        return JSON.toJSONString(object);
    }

    public ServerCfg createTestBean() {
        ServerCfg server = new ServerCfg();
        List<ServiceCfg> services = new ArrayList<>();
        ServiceCfg service = new ServiceCfg();
        List<ConnectorCfg> connectors = new ArrayList<>();
        ConnectorCfg connectorHttp11 = new ConnectorCfg();
        ConnectorCfg connectorAJP = new ConnectorCfg();

        connectorHttp11.setPort(8088);
        connectorHttp11.setProtocol("HTTP/1.1");
        connectorAJP.setPort(8089);
        connectorAJP.setProtocol("AJP");

        connectors.add(connectorHttp11);
        connectors.add(connectorAJP);

        service.setName("Kitty");
        service.setConnectorCfgs(connectors);

        services.add(service);

        server.setServices(services);

        return server;
    }


}
