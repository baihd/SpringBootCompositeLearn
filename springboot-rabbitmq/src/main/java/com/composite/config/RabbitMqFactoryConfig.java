package com.composite.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.converter.SimpleMessageConverter;

/**
 * RabbitMq配置文件读取类
 */
@Configuration
@ConfigurationProperties(prefix = "spring.rabbitmq")
public class RabbitMqFactoryConfig {

    @Value("${spring.rabbitmq.addresses}")
    private String address;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.publisher-confirms}")
    private Boolean publisherConfirms;
    @Value("${spring.rabbitmq.publisher-returns}")
    private Boolean publisherReturns;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;
    @Value("${spring.rabbitmq.template.mandatory}")
    private Boolean mandatory;

    /**
     * 构建mq实例工厂
     */
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setAddresses(address);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setPublisherConfirms(publisherConfirms);
        connectionFactory.setPublisherReturns(publisherReturns);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    /**
     * Spring AMQP提供了一个发送和接收消息的操作模板类AmqpTemplate。
     * AmqpTemplate它定义包含了发送和接收消息等的一些基本的操作功能。
     * RabbitTemplate是AmqpTemplate的一个实现。
     * RabbitTemplate支持消息的确认与返回，为了返回消息，RabbitTemplate需要设置mandatory属性为true,
     * 并且CachingConnectionFactory的publisherReturns属性也需要设置为true。
     * 返回的消息会根据它注册的RabbitTemplate.ReturnCallback setReturnCallback回调发送到给客户端，一个RabbitTemplate仅能支持一个ReturnCallback。
     * SCOPE_PROTOTYPE每次注入的时候回自动创建一个新的bean实例
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(mandatory);
        //默认持久化
        //MessageDeliveryMode.PERSISTENT
        //rabbitTemplate.setMessageConverter(new SerializerMessageConverter());
        return rabbitTemplate;
    }

    /**
     * 声明队列，交换器（Exchange），绑定（Binding）
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }


}
