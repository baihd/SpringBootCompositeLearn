package com.composite.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 用于配置交换机和队列对应关系
 * 新增消息队列应该按照如下步骤
 * 1、增加queue bean
 * 2、增加queue和exchange的binding
 * 性能排序：fanout > direct >> topic
 */
@Configuration
@AutoConfigureAfter(RabbitMqFactoryConfig.class)
public class RabbitMqExchangeConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMqExchangeConfig.class);


    @Bean
    Queue fanoutQueue1(RabbitAdmin rabbitAdmin) {
        //durable：持久化
        //exclusive：排他队列，如果一个队列被声明为排他队列，该队列仅对首次申明它的连接可见，并在连接断开时自动删除。
        //autoDelete：自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。
        Queue queue = new Queue(RabbitMqEnum.QueueName.FANOUTQUEUE1.getCode(), true, true, true);
        rabbitAdmin.declareQueue(queue);
        logger.debug("fanout测试队列实例化完成");
        return queue;
    }

    @Bean
    Queue fanoutQueue2(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitMqEnum.QueueName.FANOUTQUEUE2.getCode(), true, true, true);
        rabbitAdmin.declareQueue(queue);
        logger.debug("fanout测试队列实例化完成");
        return queue;
    }

    /**
     * 订阅型交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange(RabbitAdmin rabbitAdmin) {
        //durable：持久化
        //autoDelete：自动删除，如果该队列没有任何订阅的消费者的话，该队列会被自动删除。
        FanoutExchange fanoutExchange = new FanoutExchange(RabbitMqEnum.Exchange.CONTRACT_FANOUT.getCode(), true, true);
        rabbitAdmin.declareExchange(fanoutExchange);
        logger.debug("完成订阅型交换机bean实例化");
        return fanoutExchange;
    }

    @Bean
    Binding bindingFanoutQueue1(Queue fanoutQueue1, FanoutExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(fanoutQueue1).to(exchange);
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与直连型交换机绑定完成");
        return binding;
    }

    @Bean
    Binding bindingFanoutQueue2(Queue fanoutQueue2, FanoutExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(fanoutQueue2).to(exchange);
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与直连型交换机绑定完成");
        return binding;
    }

    @Bean
    Queue directQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitMqEnum.QueueName.DIRECTQUEUE.getCode(), true, true, true);
        rabbitAdmin.declareQueue(queue);
        logger.debug("direct测试队列实例化完成");
        return queue;
    }

    /**
     * 直连型交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    DirectExchange directExchange(RabbitAdmin rabbitAdmin) {
        DirectExchange directExchange = new DirectExchange(RabbitMqEnum.Exchange.CONTRACT_DIRECT.getCode(), true, true);
        rabbitAdmin.declareExchange(directExchange);
        logger.debug("完成直连型交换机bean实例化");
        return directExchange;
    }


    @Bean
    Binding bindingDirectQueue(Queue directQueue, DirectExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(directQueue).to(exchange).with(RabbitMqEnum.QueueEnum.DIRECTQUEUEKEY.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与直连型交换机绑定完成");
        return binding;
    }


    @Bean
    Queue topicQueue1(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitMqEnum.QueueName.TOPICQUEUE1.getCode(), true, true, true);
        rabbitAdmin.declareQueue(queue);
        logger.debug("topic测试队列1实例化完成");
        return queue;
    }

    @Bean
    Queue topicQueue2(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(RabbitMqEnum.QueueName.TOPICQUEUE2.getCode(), true, true, true);
        rabbitAdmin.declareQueue(queue);
        logger.debug("topic测试队列2实例化完成");
        return queue;
    }

    /**
     * 主题型交换机
     *
     * @param rabbitAdmin
     * @return
     */
    @Bean
    TopicExchange topicExchange(RabbitAdmin rabbitAdmin) {
        TopicExchange topicExchange = new TopicExchange(RabbitMqEnum.Exchange.CONTRACT_TOPIC.getCode(), true, true);
        rabbitAdmin.declareExchange(topicExchange);
        logger.debug("完成主题型交换机bean实例化");
        return topicExchange;

    }

    @Bean
    Binding bindingTopicQueue1(Queue topicQueue1, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(topicQueue1).to(exchange).with(RabbitMqEnum.QueueEnum.TOPICQUEUEKEY1.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与话题交换机1绑定完成");
        return binding;
    }

    @Bean
    Binding bindingTopicQueue2(Queue topicQueue2, TopicExchange exchange, RabbitAdmin rabbitAdmin) {
        Binding binding = BindingBuilder.bind(topicQueue2).to(exchange).with(RabbitMqEnum.QueueEnum.TOPICQUEUEKEY2.getCode());
        rabbitAdmin.declareBinding(binding);
        logger.debug("测试队列与话题交换机2绑定完成");
        return binding;
    }

}
