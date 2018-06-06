package com.composite;

import com.composite.entity.TestUser;
import com.composite.send.RabbitMqSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqTest {
    @Autowired
    private RabbitMqSender rabbitMqSender;

    @Test
    public void send() {
        TestUser testUser = new TestUser();
        testUser.setUsername("1");
        testUser.setPassword("123456");
        rabbitMqSender.sendFanout(testUser);
        rabbitMqSender.sendDirect("DIRECTQUEUEKEY", testUser);
        rabbitMqSender.sendTopic("lazy.1.2", testUser);
        rabbitMqSender.sendTopic("lazy.TEST.2", testUser);
    }
}
