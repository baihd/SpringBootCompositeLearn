package com.composite.config;

import com.composite.filter.SessionExpireFilter;
import com.composite.interceptor.AuthorityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.Filter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorityInterceptor authorityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //addPathPatterns用于添加拦截规则,映射为user的控制器下的所有映射,excludePathPatterns用户排除拦截
        //registry.addInterceptor(authorityInterceptor).addPathPatterns("/user/login").excludePathPatterns("/index", "/");
        registry.addInterceptor(authorityInterceptor);
        super.addInterceptors(registry);
    }

    @Bean("myFilter")
    public Filter uploadFilter() {
        return new SessionExpireFilter();
    }

    @Bean
    public FilterRegistrationBean uploadFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        /**
         * DelegatingFilterProxy作用:
         * 通过Spring容器来管理servlet filter的生命周期，
         * 如果filter中需要一些Spring容器的实例，可以通过spring直接注入，另外读取一些配置文件的操作都可以通过Spring来配置实现
         */
        registration.setFilter(new DelegatingFilterProxy("myFilter"));
        //拦截规则
        registration.addUrlPatterns("/**");
        //过滤器名称
        registration.setName("MyFilter");
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }

}
