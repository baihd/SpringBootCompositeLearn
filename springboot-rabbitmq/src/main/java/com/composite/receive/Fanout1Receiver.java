package com.composite.receive;

import com.composite.entity.TestUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FANOUTQUEUE1")
public class Fanout1Receiver {

    @RabbitHandler
    public void process(TestUser testUser) {
        System.out.println("fanout Receiver A  : " + testUser.toString());
    }
}
