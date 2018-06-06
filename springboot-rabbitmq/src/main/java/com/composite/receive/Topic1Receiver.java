package com.composite.receive;

import com.composite.entity.TestUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "TOPICQUEUE1")
public class Topic1Receiver {
    @RabbitHandler
    public void process(TestUser testUser) {
        System.out.println("Topic Receiver1:" + testUser.toString());
    }
}
