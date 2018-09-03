package com.composite.controller;

import com.composite.bean.ClientMessage;
import com.composite.bean.ReceiveMessage;
import com.composite.bean.ServerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class SubController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SimpMessagingTemplate template;


    @MessageMapping("/subscribe")
    public void subscribe(ReceiveMessage rm) {
        for (int i = 1; i <= 20; i++) {
            //广播使用convertAndSend方法，第一个参数为目的地，和js中订阅的目的地要一致
            template.convertAndSend("/topic/getResponse", rm.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @MessageMapping("/queue")
    public void queue(ReceiveMessage rm) {
        System.out.println("进入方法");
        for (int i = 1; i <= 20; i++) {
            /*广播使用convertAndSendToUser方法，第一个参数为用户id，此时js中的订阅地址为
            "/user/" + 用户Id + "/message",其中"/user"是固定的*/
            template.convertAndSendToUser("zhangsan", "/message", rm.getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    /*//客户端发送消息/app/sendTest
    //@MessageMapping接收客户端消息
    //返回消息/topic/sendTest
    //客户端订阅了/topic/sendTest主题的客户端收到消息
    @MessageMapping("/sendTest")
    public ServerMessage sendTest(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        return new ServerMessage("你发送的消息为:" + message.getName());
    }*/

    //客户端发送消息/app/sendTest
    //@MessageMapping接收客户端消息并重载目的地
    //返回消息/topic/sendTest2
    //客户端订阅了/topic/sendTest2主题的客户端收到消息
    @MessageMapping("/sendTest")
    @SendTo("/topic/sendTest2")
    public ServerMessage sendTest2(ClientMessage message) {
        logger.info("接收到了信息" + message.getName());
        return new ServerMessage("你发送的消息为:" + message.getName());
    }

    /*//客户端订阅/app/subscribeTest
    //@SubscribeMapping接收客户端订阅
    //返回消息/app/subscribeTest
    //客户端订阅了/app/subscribeTest主题的客户端收到消息
    @SubscribeMapping("/subscribeTest")
    public ServerMessage sub() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }*/

    //客户端订阅/app/subscribeTest
    //@SubscribeMapping接收客户端订阅并重载目的地
    //返回消息/app/subscribeTest
    //客户端订阅了/topic/subscribeTest主题的客户端收到消息
    @SubscribeMapping("/subscribeTest")
    @SendTo("/topic/subscribeTest")
    public ServerMessage sub2() {
        logger.info("XXX用户订阅了我。。。");
        return new ServerMessage("感谢你订阅了我。。。");
    }


}
