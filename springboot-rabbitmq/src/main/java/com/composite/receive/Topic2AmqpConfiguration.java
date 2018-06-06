package com.composite.receive;

import com.composite.config.RabbitMqEnum;
import com.composite.config.RabbitMqFactoryConfig;
import com.composite.entity.TestUser;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

@Configuration
@AutoConfigureAfter(RabbitMqFactoryConfig.class)
public class Topic2AmqpConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(Topic2AmqpConfiguration.class);

    @Bean("topicQueue2Container")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMqEnum.QueueName.TOPICQUEUE2.getCode());
        container.setMessageListener(messageListener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }


    @Bean("topicQueue2Listener")
    public ChannelAwareMessageListener messageListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                TestUser testUser = (TestUser) SerializationUtils.deserialize(message.getBody());
                logger.info("TOPICQUEUE2：" + testUser.toString());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        };
    }

}
