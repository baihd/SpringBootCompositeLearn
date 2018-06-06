package com.composite.receive;

import com.composite.entity.TestUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "FANOUTQUEUE2")
public class Fanout2Receiver {

    @RabbitHandler
    public void process(TestUser testUser) {
        System.out.println("fanout Receiver B  : " + testUser.toString());
    }
}
