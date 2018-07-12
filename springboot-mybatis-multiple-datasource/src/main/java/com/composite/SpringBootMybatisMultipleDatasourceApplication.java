package com.composite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * exclude = {DataSourceAutoConfiguration.class}
 * 禁用springboot默认加载的application.properties单数据源配置
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringBootMybatisMultipleDatasourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMybatisMultipleDatasourceApplication.class, args);
	}
}
