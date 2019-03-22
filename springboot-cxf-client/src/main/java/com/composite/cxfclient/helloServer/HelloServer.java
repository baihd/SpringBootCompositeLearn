package com.composite.cxfclient.helloServer;

import javax.xml.ws.Endpoint;

public class HelloServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9001/service/helloService", new HelloService());
        System.out.println("success");
    }
}
