package com.composite.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druid() {
        return new DruidDataSource();
    }

    //配置Druid的监控
    //配置一个管理后台的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        Map<String, String> initParams = new HashMap<>();

        //登录查看信息的账号密码
        initParams.put("loginUsername", "admin");
        initParams.put("loginPassword", "123456");
        //白名单
        initParams.put("allow", "");
        //(存在共同时，deny优先于allow):如果满足deny的话提示:Sorry,you are not permitted to view this page
        initParams.put("deny", "192.168.15.21");

        servletRegistrationBean.setInitParameters(initParams);
        return servletRegistrationBean;
    }


    //配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        //WebStatFilter用于采集web-jdbc关联监控的数据
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加不需要忽略的格式信息
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        filterRegistrationBean.setInitParameters(initParams);
        //添加过滤规则
        filterRegistrationBean.setUrlPatterns(Arrays.asList("/*"));
        return filterRegistrationBean;
    }
}
