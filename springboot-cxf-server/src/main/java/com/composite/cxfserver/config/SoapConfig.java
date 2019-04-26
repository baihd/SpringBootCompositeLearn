package com.composite.cxfserver.config;

import com.composite.cxfserver.service.AccidentService;
import com.composite.cxfserver.service.DangerService;
import com.composite.cxfserver.service.MaintainService;
import com.composite.cxfserver.service.StudentService;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SoapConfig {

    private static final String URLMAPPINGS = "/webservice/api/v1/*";

    @Autowired
    private AuthInterceptor authInterceptor;

    @Autowired
    private AccidentService accidentService;

    @Autowired
    private DangerService dangerService;

    @Autowired
    private MaintainService maintainService;

    @Autowired
    private StudentService studentService;

    private static final String ACCIDENT_ADDRESS = "/accident";

    private static final String DANGER_ADDRESS = "/danger";

    private static final String MAINTAIN_ADDRESS = "/maintain";

    private static final String STUDENT_ADDRESS = "/student";

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

    @Bean
    public EndpointImpl accidentV1() {
        EndpointImpl endpoint = new EndpointImpl(springBusV1(), accidentService);
        endpoint.publish(ACCIDENT_ADDRESS);
        //添加服务器端in权限拦截器,该AuthInterceptor就会负责检查用户名,密码是否正确
        //endpoint.getInInterceptors().add(authInterceptor);
        return endpoint;
    }

    @Bean
    public EndpointImpl dangerV1() {
        EndpointImpl endpoint = new EndpointImpl(springBusV1(), dangerService);
        endpoint.publish(DANGER_ADDRESS);
        //添加服务器端in权限拦截器,该AuthInterceptor就会负责检查用户名,密码是否正确
        //endpoint.getInInterceptors().add(authInterceptor);
        return endpoint;
    }

    @Bean
    public EndpointImpl maintainV1() {
        EndpointImpl endpoint = new EndpointImpl(springBusV1(), maintainService);
        endpoint.publish(MAINTAIN_ADDRESS);
        //添加服务器端in权限拦截器,该AuthInterceptor就会负责检查用户名,密码是否正确
        //endpoint.getInInterceptors().add(authInterceptor);
        return endpoint;
    }

    @Bean
    public EndpointImpl studentV1() {
        EndpointImpl endpoint = new EndpointImpl(springBusV1(), studentService);
        endpoint.publish(STUDENT_ADDRESS);
        //添加服务器端in权限拦截器,该AuthInterceptor就会负责检查用户名,密码是否正确
        //endpoint.getInInterceptors().add(authInterceptor);
        return endpoint;
    }

}
