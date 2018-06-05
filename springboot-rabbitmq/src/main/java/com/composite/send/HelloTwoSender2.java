package com.composite.send;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloTwoSender2 {
    @Autowired
    private AmqpTemplate template;

    public void send(int i) {
        String context = "spring boot two queue" + " ****** " + i;
        System.out.println("Sender2 : " + context);
        this.template.convertAndSend("helloTwo", context);
    }

}
