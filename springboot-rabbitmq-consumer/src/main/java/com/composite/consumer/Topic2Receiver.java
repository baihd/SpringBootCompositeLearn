package com.composite.consumer;

import com.composite.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TOPICQUEUE2")
public class Topic2Receiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("Topic Receiver2:" + user.toString());
    }
}
