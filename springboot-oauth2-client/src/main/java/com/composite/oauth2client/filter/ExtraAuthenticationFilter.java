package com.composite.oauth2client.filter;

import com.composite.oauth2client.config.IUserDetailsService;
import com.composite.oauth2client.entity.AccessToken;
import com.composite.oauth2client.entity.CheckToken;
import com.composite.oauth2client.service.OAuth2Service;
import com.composite.oauth2client.service.UserService;
import com.composite.oauth2client.service.impl.OAuth2ServiceImpl;
import com.composite.oauth2client.service.impl.UserServiceImpl;
import com.composite.oauth2client.utils.ApplicationContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExtraAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(ExtraAuthenticationFilter.class);

    private static final String FILTER_APPLY = "Spring_Security_BeforeLoginFilter_Apply";

    private String servletPath;

    private OAuth2Service oAuth2Service = ApplicationContextUtils.getBean(OAuth2ServiceImpl.class);

    private IUserDetailsService userDetailsService = ApplicationContextUtils.getBean(IUserDetailsService.class);

    private UserService userService = ApplicationContextUtils.getBean(UserServiceImpl.class);

    private AuthenticationManager authenticationManager = ApplicationContextUtils.getBean(AuthenticationManager.class);

    public ExtraAuthenticationFilter(String servletPath, String failureUrl) {
        super(servletPath);
        this.servletPath = servletPath;
        setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(failureUrl));
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if (servletRequest.getAttribute(FILTER_APPLY) != null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        servletRequest.setAttribute(FILTER_APPLY, true);
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if (servletPath.equals(request.getServletPath())) {
            String queryStr = request.getQueryString();
            if (queryStr != null && queryStr.length() != 0) {
                if (queryStr.contains("code") && queryStr.contains("=")) {
                    String[] queryStrs = queryStr.split("=");
                    if (queryStrs.length == 2) {
                        Authentication authentication = getAuthentication(queryStrs[1]);
                        if (authentication != null) {
                            SecurityContextHolder.getContext().setAuthentication(authentication);
                        } else {
                            unsuccessfulAuthentication(request, response, new UsernameNotFoundException("未找到该用户"));
                            return;
                        }
                    }
                }
            }
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {
        return null;
    }

    private Authentication getAuthentication(String code) {
        Authentication authentication = null;
        try {
            AccessToken accessToken = oAuth2Service.getAccessToken(code);
            CheckToken checkToken = oAuth2Service.getCheckToken(accessToken.getAccessToken());
            UserDetails userDetails = userDetailsService.loadUserByUsername(checkToken.getUserName());
            authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
        } catch (Exception e) {
            logger.error("getAuthentication error:" + e.getMessage());
        }
        return authentication;
    }
}
