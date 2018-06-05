package com.composite.receive;

import com.composite.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "user")
public class UserReceiver {
    @RabbitHandler
    public void process(User user) {
        System.out.println("Receiver object:" + user);
    }

}
