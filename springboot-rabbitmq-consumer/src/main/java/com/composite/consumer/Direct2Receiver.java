/*
package com.composite.consumer;

import com.composite.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RabbitListener(queues = "DIRECTQUEUE")
public class Direct2Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Direct2Receiver.class);

    @RabbitHandler
    public void process(User user) {
        logger.info("Direct2Receiver  : " + user.toString());
    }

}
*/
