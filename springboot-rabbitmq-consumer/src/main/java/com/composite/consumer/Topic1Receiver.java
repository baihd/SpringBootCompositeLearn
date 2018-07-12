package com.composite.consumer;

import com.composite.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TOPICQUEUE1")
public class Topic1Receiver {
    @RabbitHandler
    public void process(User user) {
        System.out.println("Topic Receiver1:" + user.toString());
    }
}
