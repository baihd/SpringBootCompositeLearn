package com.composite;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqChannelTest {

    private final static String QUEUE_NAME = "hello";

    @Autowired
    private Connection connection;

    @Test
    public void testSend() throws Exception {
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        for (int i = 0; i < 20; i++) {
            String message = "Hello " + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [1] Sent '" + message + "'");
        }
        channel.close();
        connection.close();
    }
}
