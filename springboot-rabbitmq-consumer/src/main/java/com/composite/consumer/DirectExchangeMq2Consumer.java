package com.composite.consumer;

import com.composite.config.RabbitMqEnum;
import com.composite.config.RabbitMqFactoryConfig;
import com.composite.entity.User;
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
public class DirectExchangeMq2Consumer {

    private static final Logger logger = LoggerFactory.getLogger(DirectExchangeMq2Consumer.class);
    //最大消费者数量
    public static final int DEFAULT_MAX_CONCURRENT = 1;
    //消费者数量
    public static final int DEFAULT_CONCURRENT = 1;
    //每个消费者获取最大投递数量
    public static final int DEFAULT_PREFETCH_COUNT = 1;

    /**
     * NONE 自动回调，即使无响应或者发生异常均会通知队列消费成功，会丢失数据。
     * AUTO 自动检测异常或者超时事件，如果发生则返回noack，消息自动回到队尾，但是这种方式可能出现消息体本身有问题，返回队尾其他队列也不能消费，造成队列阻塞。
     * MANUAL 手动回调，在程序中我们可以对消息异常记性捕获，如果出现消息体格式错误问题，手动回复ack，接着再次调用发送接口把消息推到队尾。
     */
    @Bean("MessageListenerContainer2")
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(RabbitMqEnum.QueueName.DIRECTQUEUE.getCode());
        container.setMaxConcurrentConsumers(DEFAULT_MAX_CONCURRENT);
        container.setConcurrentConsumers(DEFAULT_CONCURRENT);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        container.setPrefetchCount(DEFAULT_PREFETCH_COUNT);
        container.setMessageListener(messageListener());
        return container;
    }

    public ChannelAwareMessageListener messageListener() {
        return new ChannelAwareMessageListener() {
            @Override
            public void onMessage(Message message, Channel channel) throws Exception {
                User user = (User) SerializationUtils.deserialize(message.getBody());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
                logger.info("directQueue2:" + user.getPassword());
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //否认
                //deliveryTag:该消息的index。
                //multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
                //requeue：被拒绝的是否重新入队列。
                //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
                //确认
                //deliveryTag:该消息的index。
                //requeue：被拒绝的是否重新入队列。
                //channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            }
        };
    }
}
