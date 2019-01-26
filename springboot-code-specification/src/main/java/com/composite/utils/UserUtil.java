package com.composite.utils;

import com.composite.exception.UnLoginException;
import org.slf4j.MDC;

import java.util.Locale;

/**
 * 用户工具类
 */
public class UserUtil {
    private final static ThreadLocal<String> tlUser = new ThreadLocal<>();

    private final static ThreadLocal<Locale> tlLocale = new ThreadLocal<Locale>() {
        protected Locale initialValue() {
            return Locale.CHINESE;
        }
    };

    public static final String KEY_LANG = "lang";

    public static final String KEY_USER = "user";

    public static void setUser(String userId) {
        tlUser.set(userId);
        MDC.put(KEY_USER, userId);
    }

    public static String getUserIfLogin() {
        return tlUser.get();
    }

    public static String getUser() {
        String user = tlUser.get();
        if (user == null) {
            throw new UnLoginException();
        }
        return user;
    }

    public static void setLocale(String locale) {
        setLocale(new Locale(locale));
    }

    public static void setLocale(Locale locale) {
        tlLocale.set(locale);
    }

    public static Locale getLocale() {
        return tlLocale.get();
    }

    public static void clearAllUserInfo() {
        tlUser.remove();
        tlLocale.remove();
        MDC.remove(KEY_USER);
    }
}
