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

/**
 * 消费者配置
 */
@Configuration
@AutoConfigureAfter(RabbitMqFactoryConfig.class)
public class DirectAmqpConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(DirectAmqpConfiguration.class);

    /**
     * NONE 自动回调，即使无响应或者发生异常均会通知队列消费成功，会丢失数据。
     * AUTO 自动检测异常或者超时事件，如果发生则返回noack，消息自动回到队尾，但是这种方式可能出现消息体本身有问题，返回队尾其他队列也不能消费，造成队列阻塞。
     * MANUAL 手动回调，在程序中我们可以对消息异常记性捕获，如果出现消息体格式错误问题，手动回复ack，接着再次调用发送接口把消息推到队尾。
     */
    @Bean("directQueueContainer")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMqEnum.QueueName.DIRECTQUEUE.getCode());
        container.setMessageListener(messageListener());
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }

    @Bean("directQueueListener")
    public ChannelAwareMessageListener messageListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                TestUser testUser = (TestUser) SerializationUtils.deserialize(message.getBody());
                //通过设置TestUser的name来测试回调，分别发两条消息，一条UserName为1，一条为2，查看控制台中队列中消息是否被消费
                if ("1".equals(testUser.getUsername())) {
                    logger.info(testUser.toString());
                    System.out.println(testUser.toString());
                    //否认
                    //deliveryTag:该消息的index。
                    // multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                    // requeue：被拒绝的是否重新入队列。
                    channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                }
                if ("2".equals(testUser.getUsername())) {
                    logger.info(testUser.toString());
                    //确认
                    //deliveryTag:该消息的index。
                    //requeue：被拒绝的是否重新入队列。
                    channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                }
            }
        };
    }
}
