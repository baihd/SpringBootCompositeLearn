package com.composite.utils;

import java.security.MessageDigest;

public class MD5Util {
    private static final String SALT = "www.baidu.com";

    /**
     * MD5加盐加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        password = password + SALT;
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) {
        String hashPass = MD5Util.encode("123456");
        System.out.println(MD5Util.encode("123456"));
        System.out.println("e3bd95df26e170e45157186d313488e0".equals(hashPass));
    }

}
