package com.composite.consumer;

import com.composite.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FANOUTQUEUE2")
public class Fanout2Receiver {

    @RabbitHandler
    public void process(User user) {
        System.out.println("fanout Receiver B  : " + user.toString());
    }
}
