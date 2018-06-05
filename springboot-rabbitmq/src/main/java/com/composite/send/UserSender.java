package com.composite.send;

import com.composite.entity.User;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserSender {
    @Autowired
    private AmqpTemplate template;

    public void send(User user) {
        System.out.println("Sender object:" + user.toString());
        this.template.convertAndSend("user", user);
    }

}
