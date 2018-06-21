package com.composite.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ShaUtil {

    private static final String SALT = "www.baidu.com";

    private static final String SHA1 = "SHA-1";

    private static final String SHA256 = "SHA-256";

    private static final String SHA384 = "SHA-384";

    private static final String SHA512 = "SHA-512";

    public static String encode(String password, String sha) {
        MessageDigest digest = getMessageDigest(sha);
        digest.update(SALT.getBytes());
        byte[] bytes = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }

    private static MessageDigest getMessageDigest(String sha) {
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(sha);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return digest;
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String password = "password";
        System.out.println(encode(password, SHA1));

        System.out.println(encode(password, SHA256));

        System.out.println(encode(password, SHA384));

        System.out.println(encode(password, SHA512));
    }
}
