package com.composite.cxfserver.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class RestfulConfig {

    private static final String URLMAPPINGS = "/rest/api/v1/*";

    private static final String BASEPACKAGES = "com.composite.cxfserver.controller";

    @Bean
    public ServletRegistrationBean dispatcherRestServlet() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        //替换成自己想买的controller包路径
        context.scan(BASEPACKAGES);
        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(dispatcherServlet);
        registrationBean.setLoadOnStartup(1);
        //映射路径自定义,必须设置一个不重复的名称
        registrationBean.addUrlMappings(URLMAPPINGS);
        registrationBean.setName("rest");
        return registrationBean;
    }

}
