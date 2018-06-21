package com.composite.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptUtil {

    private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String getEncryptValue(String value) {
        return encoder.encode(value);
    }
}
