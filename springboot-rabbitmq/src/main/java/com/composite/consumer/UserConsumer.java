package com.composite.consumer;

import com.composite.entity.User;
import com.rabbitmq.client.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.SerializationUtils;

import java.io.IOException;

@Configuration
public class UserConsumer {

    private final static String QUEUE_NAME = "DIRECTQUEUE";

    private final static int PREFETCHCOUNT = 1;

    private static Channel channel() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        return channel;
    }

    @Bean
    public void consumeUser() throws Exception {
        final Channel channel = channel();
        // 消费者最多接受1条消息,直到应答后接受新消息。保证rabbitmq每次将消息发送给闲置的消费者
        channel.basicQos(PREFETCHCOUNT);
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        System.out.println("[1]Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                User user = (User) SerializationUtils.deserialize(body);
                byte[] bytes2 = SerializationUtils.serialize(user);
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties().builder().correlationId(properties.getCorrelationId()).build();
                channel.basicPublish("", properties.getReplyTo(), replyProps, bytes2);
                // 手动应答
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
    /*
     *  使用公平分发,必须关闭自动应答,使用手动应答
     *  当消费者应答rabbitmq后,rabbitmq将删除该消息
     *  保证即时消费者接受消息后(未应答)中断,rabbitmq也会将该消息发送给其他消费者而不会出现消息丢失的问题
     */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
    }
}
