package com.composite;

import com.composite.entity.User;
import com.composite.send.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitMqHelloTest {

    @Autowired
    private HelloSender helloSender;

    @Autowired
    private HelloTwoSender1 helloTwoSender1;

    @Autowired
    private HelloTwoSender2 helloTwoSender2;

    @Autowired
    private UserSender userSender;

    @Autowired
    private TopicSender topicSender;

    @Autowired
    private FanoutSender fanoutSender;


    @Test
    public void hello() {
        helloSender.send();
    }

    @Test
    public void oneToMany() {
        for (int i = 0; i < 100; i++) {
            helloTwoSender1.send(i);
            helloTwoSender2.send(i);
        }
    }

    @Test
    public void user() {
        User user = new User();
        user.setName("name1");
        user.setPassword("123456");
        userSender.send(user);
    }

    @Test
    public void topic() {
        topicSender.send1();
        topicSender.send2();
    }

    @Test
    public void fanout() {
        fanoutSender.send();
    }
}
