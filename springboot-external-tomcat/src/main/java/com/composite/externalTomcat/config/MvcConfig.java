package com.composite.externalTomcat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
public class MvcConfig extends WebMvcConfigurationSupport {


    /**
     * 表示这些配置的表示静态文件所处路径， 不用拦截
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        super.addResourceHandlers(registry);
    }

    @Bean
    public InternalResourceViewResolver setUpViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        /** 设置视图路径的前缀 */
        resolver.setPrefix("/WEB-INF/jsp/");
        /** 设置视图路径的后缀 */
        resolver.setSuffix(".jsp");
        return resolver;
    }
}
