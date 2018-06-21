package com.composite.filter;


import com.composite.entity.JwtPatternUrl;
import com.composite.entity.JwtProperty;
import com.composite.entity.ResultStatusCode;
import com.composite.utils.jwt.JwtHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 用于JWT认证的过滤器
 */
public class JwtAuthorizeFilter implements Filter {
    /*
     * 注入配置文件类
     */
    @Autowired
    private JwtProperty jwtProperty;

    @Autowired
    private JwtPatternUrl jwtPatternUrl;

    public JwtAuthorizeFilter() {
    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        Map resultMap = new HashMap<>();

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;


        if ("OPTIONS".equals(httpRequest.getMethod())) {
            chain.doFilter(httpRequest, httpResponse);

            return;
        }
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        if (isInclude(url)) {

            //如果是属于排除的URL，比如登录，注册，验证码等URL，则直接通行
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        String auth = httpRequest.getHeader("Authorization");
        boolean isRightUser = true;

        if ((auth != null) && (auth.length() > 7)) {
            String HeadStr = auth.substring(0, 6).toLowerCase();
            if (HeadStr.compareTo("bearer") == 0) {

                auth = auth.substring(7, auth.length());
                Claims claims = JwtHelper.parseJWT(auth, jwtProperty.getBase64Secret());
                if (claims != null) {

//                      //获取一下客户端传过来的关键的身份id
//                      String userId = request.getParameter("userId");
//                      String jwtUserId = (String) claims.get("userId");
//
//                      if(!userId.equals(jwtUserId)) {
//                          isRightUser = false;
//                      }

                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        //验证不通过
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        //将验证不通过的错误返回
        ObjectMapper mapper = new ObjectMapper();

        //如果身份是冒用的
        if (!isRightUser) {
            resultMap.put("status", ResultStatusCode.INVALID_USER_TOKEN.getErrorCode());
            resultMap.put("msg", ResultStatusCode.INVALID_USER_TOKEN.getErrorMsg());
        } else {
            resultMap.put("status", ResultStatusCode.INVALID_TOKEN.getErrorCode());
            resultMap.put("msg", ResultStatusCode.INVALID_TOKEN.getErrorMsg());
        }
        resultMap.put("data", null);

//          resultMsg = new ResultMsg<Object>(true, ResultStatusCode.INVALID_TOKEN.getErrorCode(), ResultStatusCode.INVALID_TOKEN.getErrorMsg(), null);
        httpResponse.getWriter().write(mapper.writeValueAsString(resultMap));
        return;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    /**
     * 是否需要过滤
     *
     * @param url
     * @return
     */
    private boolean isInclude(String url) {

        for (String patternUrl : jwtPatternUrl.getUrlPatterns()) {
            Pattern p = Pattern.compile(patternUrl);
            Matcher m = p.matcher(url);
            if (m.find()) {
                return true;
            }
        }
        return false;
    }
}
