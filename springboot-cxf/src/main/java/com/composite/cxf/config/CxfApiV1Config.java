package com.composite.cxf.config;

import com.composite.cxf.service.impl.StudentServiceImpl;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.security.JAASLoginInterceptor;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfApiV1Config {

    private static final String URLMAPPINGS = "/webservice/api/v1/*";

    private static final String addr = "/student/get";

    @Bean
    public SpringBus springBusV1() {
        SpringBus bus = new SpringBus();
        bus.setId("webservice");
        return bus;
    }

    @Bean
    public ServletRegistrationBean dispatcherWebserviceServlet() {
        CXFServlet cxfServlet = new CXFServlet();
        cxfServlet.setBus(springBusV1());
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(cxfServlet);
        registrationBean.addUrlMappings(URLMAPPINGS);
        registrationBean.setName("webservice");
        return registrationBean;
    }

    @Autowired
    private StudentServiceImpl studentServiceImpl;

    @Bean
    public EndpointImpl getStudentV1() {
        EndpointImpl endpoint = new EndpointImpl(springBusV1(), studentServiceImpl);
        endpoint.publish(addr);
        //添加服务器端in权限拦截器,该AuthInterceptor就会负责检查用户名,密码是否正确
        endpoint.getInInterceptors().add(new AuthInterceptor());
        return endpoint;
    }

}
