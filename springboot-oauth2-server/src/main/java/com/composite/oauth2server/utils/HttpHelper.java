package com.composite.oauth2server.utils;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {

    public static boolean isAjaxRequest(HttpServletRequest request) {
        String ajaxFlag = request.getHeader("X-Requested-With");
        return ajaxFlag != null && "XMLHttpRequest".equals(ajaxFlag);
    }
}
