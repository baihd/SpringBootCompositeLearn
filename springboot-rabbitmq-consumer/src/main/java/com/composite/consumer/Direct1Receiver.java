/*
package com.composite.consumer;

import com.composite.entity.User;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
@RabbitListener(queues = "DIRECTQUEUE")
public class Direct1Receiver {
    private static final Logger logger = LoggerFactory.getLogger(Direct1Receiver.class);

    @RabbitHandler
    public void process(User user, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        logger.info("DirectReceiver  : " + user.toString());
    }

}
*/
