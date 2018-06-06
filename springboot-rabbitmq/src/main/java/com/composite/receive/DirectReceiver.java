package com.composite.receive;

import com.composite.entity.TestUser;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RabbitListener(queues ="DIRECTQUEUE" )
public class DirectReceiver {

    @RabbitHandler
    public void process(TestUser object){
        System.out.println("DirectReceiver  : " + object.toString());
    }

}
