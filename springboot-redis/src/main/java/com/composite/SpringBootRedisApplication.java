package com.composite;

import com.composite.config.SpringUtils;
import com.composite.message.Receiver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class SpringBootRedisApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(SpringBootRedisApplication.class, args);
        StringRedisTemplate template = SpringUtils.getBean(StringRedisTemplate.class);
        CountDownLatch latch = SpringUtils.getBean(CountDownLatch.class);
        template.convertAndSend("chat", "Hello from Redis!");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    //1
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    //2
    @Bean
    CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    //注入消息接收者3
    @Bean
    Receiver receiver(CountDownLatch latch) {
        return new Receiver(latch);
    }


    //4
    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    //5
    @Bean
    RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("chat"));
        return container;
    }

}
