package com.composite.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    /**
     * 对密码进行加密
     *
     * @param password
     * @return
     */
    public static String encode(String password) {
        return encoder.encode(password);
    }

    /**
     * 对原密码和已加密的密码进行匹配，判断是否相等
     * @param password
     * @param encodePassword
     * @return
     */
    public static boolean match(String password, String encodePassword) {
        return encoder.matches(password, encodePassword);
    }

    public static void main(String[] args) {
        String hashPass = encode("123456");
        System.out.println(hashPass);
        System.out.println(match("123456",hashPass));//true
        System.out.println(match("123456","$2a$10$7wOQPHU2MfHt3X4wCFx5H.EZu.rlHMtY5HTFsqXiPd6BA5vNHJNf2"));//true
        System.out.println(match("123456","$2a$10$nYQWXcY.eVUwI8kYGtMCVOD0hWE4AKjzFg0oo91qc/ECQg/DD/CpS"));//true
        System.out.println(match("123456","$2a$10$9etIPtquQ3f..ACQkDHAVuBfjBoDXXWHHCOBl/RaJADxuXdSQB6I2"));//true
    }
}
