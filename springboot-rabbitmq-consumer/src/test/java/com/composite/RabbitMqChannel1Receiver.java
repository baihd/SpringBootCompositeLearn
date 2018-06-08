package com.composite;

import com.rabbitmq.client.*;

import java.io.IOException;

public class RabbitMqChannel1Receiver {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setUsername("guest");
        factory.setPassword("guest");

        Connection connection = factory.newConnection();
        final Channel channel = connection.createChannel();

        // 消费者最多接受1条消息,直到应答后接受新消息。保证rabbitmq每次将消息发送给闲置的消费者
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        System.out.println("[1]Waiting for messages");
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[1]Received '" + message + "'");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

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
