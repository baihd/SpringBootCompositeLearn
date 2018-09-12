package com.composite.springboot2oauth2server;

import org.apache.commons.codec.binary.Base64;

public class Test {

    public static void main(String[] args) {
        String authHeader = getAuthorization("client1", "secret");
        boolean value = authHeader.equals("Basic Y2xpZW50MTpzZWNyZXQ=");
        System.out.println(authHeader);
        System.out.println(value);
    }

    private static String getAuthorization(String client, String secret) {
        String auth = client + ":" + secret;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes());
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}
