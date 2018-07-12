package com.composite;

import com.composite.entity.User;
import com.composite.producer.RabbitMqProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    private RabbitMqProducer rabbitMqSender;

    @Test
    public void send() {
        User user = new User();
        user.setUsername("1");
        //rabbitMqSender.sendFanout(testUser);
        for (int i = 0; i < 50; i++) {
            user.setPassword(String.valueOf(i));
            rabbitMqSender.sendDirect("DIRECTQUEUEKEY", user);
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (int i = 50; i < 100; i++) {
            user.setPassword(String.valueOf(i));
            rabbitMqSender.sendDirect("DIRECTQUEUEKEY", user);
        }
        //rabbitMqSender.sendDirect("DIRECTQUEUEKEY", testUser);

        //rabbitMqSender.sendTopic("lazy.1.2", testUser);
        //rabbitMqSender.sendTopic("lazy.TEST.2", testUser);
    }
}
